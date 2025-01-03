package org.oa.mindbook.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.auth.CustomUserDetails;
import org.oa.mindbook.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.SignatureException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("[ JwtAuthorizationFilter ] 인가 필터 작동");

        try {
            //Request 에서 access token 추출
            String accessToken = jwtUtil.resolveAccessToken(request);

            // accessToken 없이 접근할 경우 필터를 건너뜀
            if (accessToken == null) {
                log.info("[ JwtAuthorizationFilter ] Access Token 이 존재하지 않음. 필터를 건너뜁니다.");
                filterChain.doFilter(request, response);
                return;
            }

            authenticateAccessToken(accessToken);
            log.info("[ JwtAuthorizationFilter ] 종료. 다음 필터로 넘어갑니다.");
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            logger.warn("[ JwtAuthorizationFilter ] accessToken 이 만료되었습니다.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 401 return
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Access Token 이 만료되었습니다.");
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }
    }

    private void authenticateAccessToken(String accessToken) throws SignatureException {
        log.info("[ JwtAuthorizationFilter ] 토큰으로 인가 과정을 시작합니다. ");

        //AccessToken 유효성 검증
        jwtUtil.validateToken(accessToken);
        log.info("[ JwtAuthorizationFilter ] Access Token 유효성 검증 성공. ");

        //CustomUserDetail 객체 생성
        CustomUserDetails userDetails = new CustomUserDetails(
                jwtUtil.getEmail(accessToken),
                null,
                jwtUtil.getRoles(accessToken)
        );

        log.info("[ JwtAuthorizationFilter ] UserDetails 객체 생성 성공");

        // Spring Security 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());

        // SecurityContextHolder 에 현재 인증 객체 저장
        SecurityContextHolder.getContext().setAuthentication(authToken);

        log.info("[ JwtAuthorizationFilter ] 인증 객체 저장 완료");
    }
}