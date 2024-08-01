package org.oa.mindbook.Controller.Memoir;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateJoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.JoyMemoirService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/joyMemoir")
@Tag(name = "기쁨 회고록 API", description = "기쁨 회고록 관련 API입니다.")
public class JoyMemoirController {

    private final JoyMemoirService joyMemoirService;

    @Operation(method = "POST", summary = "기쁨 회고록 작성")
    @PostMapping("")
    public ResponseEntity<?> createJoyMemoir(@RequestBody CreateJoyMemoirRequestDto createJoyMemoirRequestDto) {
        log.info("오늘 있었던 일: {}", createJoyMemoirRequestDto.getMemory());
        log.info("느낀점: {}",createJoyMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createJoyMemoirRequestDto.getStatus());

        Long JoyMemoirId = joyMemoirService.saveJoyMemoir(createJoyMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(JoyMemoirId);
    }

    @Operation(method = "GET", summary = "기쁨 회고록 상세 조회")
    @GetMapping("/detail")
    public JoyMemoirResponseDto getJoyMemoir(@RequestParam Long userId, @RequestParam Long joyMemoirId) {
        return joyMemoirService.getJoyMemoir(joyMemoirId, userId);
    }

    @Operation(method = "GET", summary = "기쁨 회고록 목록 작성")
    @GetMapping("")
    public List<JoyMemoirListResponseDto> getJoyMemoirList(@RequestParam String status, @RequestParam Long userId) {
        return joyMemoirService.getJoyMemoirList(status, userId);
    }




}
