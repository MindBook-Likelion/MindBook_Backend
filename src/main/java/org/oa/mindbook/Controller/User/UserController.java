package org.oa.mindbook.Controller.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.User.CreateUserRequestDto;
import org.oa.mindbook.Dto.request.User.UpdateUserRequestDto;
import org.oa.mindbook.Dto.response.User.UserResponseDto;
import org.oa.mindbook.Service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j //로그 출력을 도와주는 어노테이션
@RestController
@RequiredArgsConstructor
@RequestMapping("/user") // uri가 /user로 시작하는 요청을 받습니다.
@Tag(name = "유저 API", description = "유저 API입니다.")
public class UserController {

    private final UserService userService;

    @Operation(method = "POST", summary = "유저 생성")
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserResponseDto responseDto = userService.createUser(createUserRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getUser(userDetails.getUsername()));
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateUserRequestDto userUpdateRequestDto) {
        UserResponseDto responseDto = userService.updateUser(userDetails.getUsername(), userUpdateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}