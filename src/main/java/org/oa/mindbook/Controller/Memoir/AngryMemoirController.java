package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateAngryMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AngryMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.AngryMemoirService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/angryMemoir")
public class AngryMemoirController {

    private final AngryMemoirService angryMemoirService;

    @PostMapping("")
    public String createAngryMemoir(@RequestBody CreateAngryMemoirRequestDto createAngryMemoirRequestDto) {
        log.info("유저아이디: {}", createAngryMemoirRequestDto.getUserId());
        log.info("오늘 있었던 일: {}", createAngryMemoirRequestDto.getMemory());
        log.info("느낀점: {}", createAngryMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createAngryMemoirRequestDto.getStatus());

        angryMemoirService.saveAngryMemoir(createAngryMemoirRequestDto);

        return "화남 회고록이 작성되었습니다.";
    }

    @GetMapping("/{angryMemoirId}")
    public AngryMemoirResponseDto getAngryMemoir(@PathVariable Long angryMemoirId) {
        return angryMemoirService.getAngryMemoir(angryMemoirId);
    }
}
