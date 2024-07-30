package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateAnxietyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AnxietyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AnxietyMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.AnxietyMemoirService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/anxietyMemoir")
public class AnxietyMemoirController {

    private final AnxietyMemoirService anxietyMemoirService;

    @PostMapping("")
    public ResponseEntity<?> createAnxietyMemoir(@RequestBody CreateAnxietyMemoirRequestDto createAnxietyMemoirRequestDto) {
        log.info("오늘 있었던 일: {}", createAnxietyMemoirRequestDto.getMemory());
        log.info("느낀점: {}",createAnxietyMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createAnxietyMemoirRequestDto.getStatus());

        Long AnxietyMemoirId = anxietyMemoirService.saveAnxietyMemoir(createAnxietyMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(AnxietyMemoirId);
    }

    @GetMapping("/detail")
    public AnxietyMemoirResponseDto getAnxietyMemoir(@RequestParam Long userId, @RequestParam Long anxietyMemoirId) {
        return anxietyMemoirService.getAnxietyMemoir(anxietyMemoirId, userId);
    }

    @GetMapping("")
    public List<AnxietyMemoirListResponseDto> getAnxietyMemoirList(@RequestParam String status, @RequestParam Long userId) {
        return anxietyMemoirService.getAnxietyMemoirList(status, userId);
    }
}
