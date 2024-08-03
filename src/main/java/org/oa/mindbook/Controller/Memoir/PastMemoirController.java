package org.oa.mindbook.Controller.Memoir;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreatePastMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.PastMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.PastMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.PastMemoirService;
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
@RequestMapping("/pastMemoir")
@Tag(name = "추억 회고록 API", description = "추억 회고록 관련 API입니다.")
public class PastMemoirController {

    private final PastMemoirService pastMemoirService;
    private final UserService userService;

    @Operation(method = "POST", summary = "추억 회고록 작성")
    @PostMapping("")
    public ResponseEntity<?> createPastMemoir(@RequestBody CreatePastMemoirRequestDto createPastMemoirRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        Long PastMemoirId = pastMemoirService.savePastMemoir(userId ,createPastMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(PastMemoirId);
    }

    @Operation(method = "GET", summary = "추억 회고록 상세 조회")
    @GetMapping("/detail")
    public PastMemoirResponseDto getPastMemoir(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long pastMemoirId) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        return pastMemoirService.getPastMemoir(pastMemoirId, userId);
    }

    @Operation(method = "GET", summary = "추억 회고록 목록 작성")
    @GetMapping("")
    public List<PastMemoirListResponseDto> getSadMemoirList(@RequestParam String status, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        return pastMemoirService.getPastMemoirList(status, userId);
    }
}
