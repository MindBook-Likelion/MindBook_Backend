package org.oa.mindbook.Controller.Memoir;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateAnxietyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AnxietyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AnxietyMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.AnxietyMemoirService;
import org.oa.mindbook.Service.User.UserService;
import org.oa.mindbook.auth.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/anxietyMemoir")
@Tag(name = "불안 회고록 API", description = "불안 회고록 관련 API입니다.")
public class AnxietyMemoirController {

    private final AnxietyMemoirService anxietyMemoirService;
    private final UserService userService;

    @Operation(method = "POST", summary = "불안 회고록 작성")
    @PostMapping("")
    public ResponseEntity<?> createAnxietyMemoir(@RequestBody CreateAnxietyMemoirRequestDto createAnxietyMemoirRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        Long AnxietyMemoirId = anxietyMemoirService.saveAnxietyMemoir(userId,createAnxietyMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(AnxietyMemoirId);
    }

    @Operation(method = "GET", summary = "불안 회고록 상세 조회")
    @GetMapping("/detail")
    public AnxietyMemoirResponseDto getAnxietyMemoir(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long anxietyMemoirId) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        return anxietyMemoirService.getAnxietyMemoir(anxietyMemoirId, userId);
    }

    @Operation(method = "GET", summary = "불안 회고록 목록 조회")
    @GetMapping("")
    public List<AnxietyMemoirListResponseDto> getAnxietyMemoirList(@RequestParam String status, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        return anxietyMemoirService.getAnxietyMemoirList(status, userId);
    }


    @Operation(method = "GET", summary = "나의 불안 회고록 목록 조회")
    @GetMapping("/all")
    public List<AnxietyMemoirListResponseDto> getAnxietyMemoirList(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        return anxietyMemoirService.getMyAnxietyMemoirList(userId);
    }
}
