package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.CreatePastMemoirRequestDto;
import org.oa.mindbook.Service.Memoir.PastMemoirService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pastMemoir")
public class PastMemoirController {

    private final PastMemoirService pastMemoirService;

    @PostMapping("")
    public String createPastMemoir(@RequestBody CreatePastMemoirRequestDto createPastMemoirRequestDto) {
        log.info("유저아이디: {}", createPastMemoirRequestDto.getUserId());
        log.info("추억하고 싶은 날짜: {}", createPastMemoirRequestDto.getPastAt());
        log.info("오늘 있었던 일: {}", createPastMemoirRequestDto.getMemory());
        log.info("느낌점: {}", createPastMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createPastMemoirRequestDto.getStatus());

        pastMemoirService.savePastMemoir(createPastMemoirRequestDto);

        return "추억 회고록이 작성되었습니다.";
    }
}