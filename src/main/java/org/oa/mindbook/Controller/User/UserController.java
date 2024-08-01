package org.oa.mindbook.Controller.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.oa.mindbook.Dto.request.User.UserReqDto;
import org.oa.mindbook.Service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "유저 API", description = "유저 API입니다.")
public class UserController {

    private final UserService userService;

    @Operation(method = "POST", summary = "유저 생성")
    @PostMapping("/register")
    public ResponseEntity<?> savedUser(@RequestBody UserReqDto userReqDto) {
        try {
            Long userId = userService.save(userReqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1L);
        }
    }
}
