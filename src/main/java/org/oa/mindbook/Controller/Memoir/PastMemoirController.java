package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreatePastMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.PastMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.PastMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.PastMemoirService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pastMemoir")
public class PastMemoirController {

    private final PastMemoirService pastMemoirService;

    @PostMapping("")
    public ResponseEntity<?> createPastMemoir(@RequestBody CreatePastMemoirRequestDto createPastMemoirRequestDto) {
        log.info("추억하고 싶은 날짜: {}", createPastMemoirRequestDto.getPastAt());
        log.info("오늘 있었던 일: {}", createPastMemoirRequestDto.getMemory());
        log.info("느낌점: {}", createPastMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createPastMemoirRequestDto.getStatus());

        Long PastMemoirId = pastMemoirService.savePastMemoir(createPastMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(PastMemoirId);
    }

    @GetMapping("/detail")
    public PastMemoirResponseDto getPastMemoir(@RequestParam Long userId, @RequestParam Long pastMemoirId) {
        return pastMemoirService.getPastMemoir(pastMemoirId, userId);
    }

    @GetMapping("")
    public List<PastMemoirListResponseDto> getSadMemoirList(@RequestParam String status, @RequestParam Long userId) {
        return pastMemoirService.getPastMemoirList(status, userId);
    }
}
