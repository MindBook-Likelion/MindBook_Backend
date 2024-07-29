package org.oa.mindbook.Controller.User;

import lombok.RequiredArgsConstructor;
import org.oa.mindbook.Dto.request.User.UserReqDto;
import org.oa.mindbook.Service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<?> savedUser(@RequestBody UserReqDto userReqDto) {
        try {
            Long userId = userService.save(userReqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1L);
        }
    }
}
