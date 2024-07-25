package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.CreateAnnoyMemoirRequestDto;
import org.oa.mindbook.Service.Memoir.AnnoyMemoirService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/annoyMemoir")
public class AnnoyMemoirController {

    private final AnnoyMemoirService annoyMemoirService;

    @PostMapping("")
    public String createAnnoyMemoir(@RequestBody CreateAnnoyMemoirRequestDto createAnnoyMemoirRequestDto) {
        log.info("유저아이디: {}", createAnnoyMemoirRequestDto.getUserId());
        log.info("오늘 있었던 일: {}", createAnnoyMemoirRequestDto.getMemory());
        log.info("느낀점: {}",createAnnoyMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createAnnoyMemoirRequestDto.getStatus());

        annoyMemoirService.saveAnnoyMemoir(createAnnoyMemoirRequestDto);

        return "짜증 회고록이 작성되었습니다.";
    }

}
