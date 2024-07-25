package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.CreateAnxietyMemoirRequestDto;
import org.oa.mindbook.Service.Memoir.AnxietyMemoirService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/anxietyMemoir")
public class AnxietyMemoirController {

    private final AnxietyMemoirService anxietyMemoirService;

    @PostMapping("")
    public String createAnxiety(@RequestBody CreateAnxietyMemoirRequestDto createAnxietyMemoirRequestDto) {
        log.info("유저아이디: {}", createAnxietyMemoirRequestDto.getUserId());
        log.info("오늘 있었던 일: {}", createAnxietyMemoirRequestDto.getMemory());
        log.info("느낀점: {}",createAnxietyMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createAnxietyMemoirRequestDto.getStatus());

        anxietyMemoirService.saveAnxietyMemoir(createAnxietyMemoirRequestDto);

        return "불안 회고록이 작성되었습니다.";
    }
}
