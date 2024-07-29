package org.oa.mindbook.Controller.Memoir;

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
public class JoyMemoirController {

    private final JoyMemoirService joyMemoirService;

    @PostMapping("")
    public ResponseEntity<?> createJoyMemoir(@RequestBody CreateJoyMemoirRequestDto createJoyMemoirRequestDto) {
        log.info("오늘 있었던 일: {}", createJoyMemoirRequestDto.getMemory());
        log.info("느낀점: {}",createJoyMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createJoyMemoirRequestDto.getStatus());

        Long JoyMemoirId = joyMemoirService.saveJoyMemoir(createJoyMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(JoyMemoirId);
    }

    @GetMapping("/detail")
    public JoyMemoirResponseDto getJoyMemoir(@RequestParam Long userId, @RequestParam Long joyMemoirId) {
        return joyMemoirService.getJoyMemoir(joyMemoirId, userId);
    }

    @GetMapping("")
    public List<JoyMemoirListResponseDto> getJoyMemoirList(@RequestParam String status) {
        return joyMemoirService.getJoyMemoirList(status);
    }




}
