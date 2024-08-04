package org.oa.mindbook.Controller.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.User.CreateUserRequestDto;
import org.oa.mindbook.Dto.request.User.LoginRequestDto;
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
    @Operation(summary = "회원가입", description = "회원가입")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserResponseDto responseDto = userService.createUser(createUserRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 유저 정보 조회
    @GetMapping("/info")
    @Operation(summary = "유저 정보 조회", description = "유저의 이메일, 닉네임, 유저아이디를 조회할 수 있습니다.")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getUser(userDetails.getUsername()));
    }

    // 비밀번호 변경
    @PutMapping("/update")
    @Operation(summary = "비밀번호 변경", description = "비밀번호를 변경할 수 있습니다.")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateUserRequestDto userUpdateRequestDto) {
        UserResponseDto responseDto = userService.updateUser(userDetails.getUsername(), userUpdateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 탈퇴
    @DeleteMapping("/withdraw")
    @Operation(summary = "탈퇴", description = "DB에서 유저정보를 삭제하여 탈퇴합니다.")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    //비밀번호 재발급
    @Operation(summary = "임시 비밀번호 발급 ", description = "Query String : email 전달 시 서버에서 사용자 이메일로 임시 비밀번호를 보내고, 사용자 비밀번호를 해당 비밀번호로 변경")
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
    @Operation(summary = "가입한 날짜 조회", description = "회원가입한 날짜로부터 며칠이 지났는지 알려줍니다. \n 예: ㅇㅇ님이 마음책방에 오신지 10일 되었어요! 😀")
    public ResponseEntity<?> getCreatedAt(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername(); // 이메일을 가져옴
        try {
            String message = userService.getDaysSinceJoinedByEmail(email); // 서비스 메서드를 호출하여 이메일로 닉네임을 조회
            return ResponseEntity.ok(message);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(method = "POST", summary = "로그인", description = "로그인합니다. email과 password를 body에 담아서 전송합니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){
        return null;
    }

    @Operation(method = "POST", summary = "로그아웃", description = "로그아웃합니다. accessToken을 header에 담아서 전송합니다. ")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserDetails userDetails) {
        return null;
    }
}