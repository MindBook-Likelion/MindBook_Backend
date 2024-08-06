package org.oa.mindbook.Controller.Memoir;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateSadMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.SadMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.SadMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.SadMemoirService;
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
@RequestMapping("/sadMemoir")
@Tag(name = "슬픔 회고록 API", description = "슬픔 회고록 관련 API입니다.")
public class SadMemoirController {

    private final SadMemoirService sadMemoirService;
    private final UserService userService;

    @Operation(method = "POST", summary = "슬픔 회고록 작성")
    @PostMapping("")
    public ResponseEntity<?> createSadMemoir(@RequestBody CreateSadMemoirRequestDto createSadMemoirRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        Long SadMemoirId = sadMemoirService.saveSadMemoir(userId, createSadMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(SadMemoirId);
    }

    @Operation(method = "GET", summary = "슬픔 회고록 상세 조회")
    @GetMapping("/detail")
    public SadMemoirResponseDto getSadMemoir(@RequestParam Long sadMemoirId, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        return sadMemoirService.getSadMemoir(sadMemoirId, userId);
    }

    @Operation(method = "GET", summary = "슬픔 회고록 목록 조회")
    @GetMapping("")
    public List<SadMemoirListResponseDto> getSadMemoirList(@RequestParam String status, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        return sadMemoirService.getSadMemoirList(status, userId);
    }

    @Operation(method = "GET", summary = "나의 슬픔 회고록 목록 조회")
    @GetMapping("/all")
    public List<SadMemoirListResponseDto> getSadMemoirList( @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        return sadMemoirService.getMySadMemoirList(userId);
    }
}
