//package org.oa.mindbook.Config;
//
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;
//import org.apache.catalina.filters.CorsFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        // 허용되는 오리진 설정
//        configuration.setAllowedOriginPatterns(List.of(
//                "http://localhost:8000",
//                "http://localhost:8080",
//                "http://localhost:3000",
//                "http://localhost:5500",
//                "http://localhost:5501",
//                "http://localhost:3453",
//                "http://localhost:8800",
//                "http://127.0.0.1:8080",
//                "http://3.38.119.114:8080"
//        ));
//
//        // 허용되는 HTTP 메소드 설정
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//
//        // 허용되는 헤더 설정
//        configuration.setAllowedHeaders(Arrays.asList("*", "Authorization", "Content-Type"));
//
//        // 인증 정보 허용
//        configuration.setAllowCredentials(true);
//
//        source.registerCorsConfiguration("/**", configuration);
//        return new CorsFilter(source);
//    }
//}