package org.oa.mindbook.Config;

import lombok.RequiredArgsConstructor;
import org.oa.mindbook.filter.CustomLoginFilter;
import org.oa.mindbook.filter.JwtAuthorizationFilter;
import org.oa.mindbook.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration // 빈 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    //인증이 필요하지 않은 url
    private final String[] allowedUrls = {
            "/user/login", //로그인은 인증이 필요하지 않음
            "/user/register", //회원가입은 인증이 필요하지 않음
            "/user/emailCheck", // 이메일 인증 요청은 인증이 필요하지 않음
            "/user/verification", // 이메일 유효성 확인은 인증이 필요하지 않음
            "/user/findPw", // 비밀번호 재발급은 인증이 필요하지 않음
            "/auth/reissue", //토큰 재발급은 인증이 필요하지 않음
            "/auth/**",
            "/swagger-ui/**",   // swagger 관련 URL
            "/v3/api-docs/**",
    };
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CORS 정책 설정
        http
                .cors(cors -> cors
                        .configurationSource(CorsConfig.apiConfigurationSource()));
        // csrf 비활성화
        http
                .csrf(AbstractHttpConfigurer::disable);
        // form 로그인 방식 비활성화 -> REST API 로그인을 사용할 것이기 때문에
        http
                .formLogin(AbstractHttpConfigurer::disable);
        // logout url 설정. 인증을 위해 사용하는 쿠키인 JSESSIONID를 삭제하여 로그아웃 처리
        http
                .logout((configurer) ->
                        configurer
                                .logoutUrl("/user/logout")
                                .deleteCookies("JSESSIONID")
                )
                //인증되지 않은 자원에 접근했을 때
                .exceptionHandling((configurer) ->
                        configurer
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN)));
        // http basic 인증 방식 비활성화
        http
                .httpBasic(AbstractHttpConfigurer::disable);
        // 세션을 사용하지 않음. (세션 생성 정책을 Stateless 설정.)
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 경로별 인가
        http
                .authorizeHttpRequests(auth -> auth
                        //위에서 정의했던 allowedUrls 들은 인증이 필요하지 않음 -> permitAll
                        .requestMatchers(allowedUrls).permitAll()
                        .requestMatchers(HttpMethod.GET, "/searchBook").permitAll()
                        .anyRequest().authenticated() // 그 외의 url 들은 인증이 필요함
                );
        // Login Filter
        CustomLoginFilter loginFilter = new CustomLoginFilter(
                authenticationManager(authenticationConfiguration), jwtUtil);
        // Login Filter URL 지정
        loginFilter.setFilterProcessesUrl("/user/login");
        // filter chain 에 login filter 등록
        http
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
        // login filter 전에 Auth Filter 등록
        http
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), CustomLoginFilter.class);
        return http.build();
    }
}