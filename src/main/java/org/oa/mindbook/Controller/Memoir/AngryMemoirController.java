package org.oa.mindbook.Controller.Memoir;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "화남 회고록 API", description = "화남 회고록 관련 API입니다.")
public class AngryMemoirController {

    private final AngryMemoirService angryMemoirService;

    @Operation(method = "POST", summary = "화남 회고록 작성")
    @PostMapping("")
    public ResponseEntity<?> createAngryMemoir(@RequestBody CreateAngryMemoirRequestDto createAngryMemoirRequestDto) {
        log.info("오늘 있었던 일: {}", createAngryMemoirRequestDto.getMemory());
        log.info("느낀점: {}", createAngryMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createAngryMemoirRequestDto.getStatus());

        Long AngryMemoirId = angryMemoirService.saveAngryMemoir(createAngryMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(AngryMemoirId);
    }

    @Operation(method = "GET", summary = "화남 회고록 상세 조회")
    @GetMapping("/detail")
    public AngryMemoirResponseDto getAngryMemoir(@RequestParam Long userId, @RequestParam Long angryMemoirId) {
        return angryMemoirService.getAngryMemoir(angryMemoirId, userId);
    }

    @Operation(method = "GET", summary = "화남 회고록 목록 작성")
    @GetMapping("")
    public List<AngryMemoirListResponseDto> getAngryMemoirList(@RequestParam String status, @RequestParam Long userId) {
        return angryMemoirService.getAngryMemoirList(status, userId);
    }
}
