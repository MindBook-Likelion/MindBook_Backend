package org.oa.mindbook.Controller.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.User.JwtDto;
import org.oa.mindbook.Service.User.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    //토큰 재발급 API
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody JwtDto jwtDto) throws SignatureException {

        log.info("[ Auth Controller ] 토큰을 재발급합니다. ");

        return ResponseEntity.ok(authService.reissueToken(jwtDto));
    }
}