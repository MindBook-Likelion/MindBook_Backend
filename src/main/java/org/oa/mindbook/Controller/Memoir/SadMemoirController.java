package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateSadMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.SadMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.SadMemoirService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sadMemoir")
public class SadMemoirController {

    private final SadMemoirService sadMemoirService;

    @PostMapping("")
    public String createSadMemoir(@RequestBody CreateSadMemoirRequestDto createSadMemoirRequestDto) {
        log.info("유저아이디: {}", createSadMemoirRequestDto.getUserId());
        log.info("오늘 있었던 일: {}", createSadMemoirRequestDto.getMemory());
        log.info("느낀점: {}", createSadMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createSadMemoirRequestDto.getStatus());

        sadMemoirService.saveSadMemoir(createSadMemoirRequestDto);

        return "슬픔 회고록이 작성되었습니다.";
    }

    @GetMapping("/{sadMemoirId}")
    public SadMemoirResponseDto getSadMemoir(@PathVariable Long sadMemoirId) {
        return sadMemoirService.getSadMemoir(sadMemoirId);
    }
}
