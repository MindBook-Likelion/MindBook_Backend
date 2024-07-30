package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateAngryMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AngryMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AngryMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.AngryMemoirService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/angryMemoir")
public class AngryMemoirController {

    private final AngryMemoirService angryMemoirService;

    @PostMapping("")
    public ResponseEntity<?> createAngryMemoir(@RequestBody CreateAngryMemoirRequestDto createAngryMemoirRequestDto) {
        log.info("오늘 있었던 일: {}", createAngryMemoirRequestDto.getMemory());
        log.info("느낀점: {}", createAngryMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createAngryMemoirRequestDto.getStatus());

        Long AngryMemoirId = angryMemoirService.saveAngryMemoir(createAngryMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(AngryMemoirId);
    }

    @GetMapping("/detail")
    public AngryMemoirResponseDto getAngryMemoir(@RequestParam Long userId, @RequestParam Long angryMemoirId) {
        return angryMemoirService.getAngryMemoir(angryMemoirId, userId);
    }

    @GetMapping("")
    public List<AngryMemoirListResponseDto> getAngryMemoirList(@RequestParam String status, @RequestParam Long userId) {
        return angryMemoirService.getAngryMemoirList(status, userId);
    }
}
