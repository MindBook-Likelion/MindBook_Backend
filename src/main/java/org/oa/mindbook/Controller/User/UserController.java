package org.oa.mindbook.Controller.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.User.CreateUserRequestDto;
import org.oa.mindbook.Dto.request.User.UpdateUserRequestDto;
import org.oa.mindbook.Dto.response.User.UserResponseDto;
import org.oa.mindbook.Service.User.EmailService;
import org.oa.mindbook.Service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "유저 API", description = "유저 API입니다.")
public class UserController {

    private final UserService userService;

    private final EmailService emailService;

    //회원가입
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserResponseDto responseDto = userService.createUser(createUserRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 유저 정보 조회
    @GetMapping("/info")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getUser(userDetails.getUsername()));
    }

    // 비밀번호 변경
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateUserRequestDto userUpdateRequestDto) {
        UserResponseDto responseDto = userService.updateUser(userDetails.getUsername(), userUpdateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 탈퇴
    @DeleteMapping("/withdraw")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    //비밀번호 재발급
    @Operation(summary = "사용자 비밀번호 재발급 ", description = "Query String : email 전달 시 서버에서 사용자 이메일로 임시 비밀번호를 보내고, 사용자 비밀번호를 해당 비밀번호로 변경")
    @PostMapping("/findPw")
    public ResponseEntity<?> passWordReissuance(@RequestParam("email") String email) {
        try {
            emailService.sendMessageForPassword(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 가입한 날짜 조회
    @GetMapping("/createdAt")
    public ResponseEntity<?> getCreatedAt(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername(); // 이메일을 가져옴
        try {
            String message = userService.getDaysSinceJoinedByEmail(email); // 서비스 메서드를 호출하여 이메일로 닉네임을 조회
            return ResponseEntity.ok(message);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}