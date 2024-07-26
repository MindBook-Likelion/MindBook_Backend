package org.oa.mindbook.Controller.Memoir;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateJoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.JoyMemoirService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/joyMemoir")
public class JoyMemoirController {

    private final JoyMemoirService joyMemoirService;

    @PostMapping("")
    public String createJoyMemoir(@RequestBody CreateJoyMemoirRequestDto createJoyMemoirRequestDto) {
        log.info("유저아이디: {}", createJoyMemoirRequestDto.getUserId());
        log.info("오늘 있었던 일: {}", createJoyMemoirRequestDto.getMemory());
        log.info("느낀점: {}",createJoyMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createJoyMemoirRequestDto.getStatus());

        joyMemoirService.saveJoyMemoir(createJoyMemoirRequestDto);

        return "기쁨 회고록이 작성되었습니다.";
    }

    @GetMapping("/{joyMemoirId}")
    public JoyMemoirResponseDto getJoyMemoir(@PathVariable Long joyMemoirId) {
        return joyMemoirService.getJoyMemoir(joyMemoirId);
    }

    @GetMapping("")
    public List<JoyMemoirListResponseDto> getJoyMemoirList(@RequestParam String status) {
        return joyMemoirService.getJoyMemoirList(status);
    }




}
