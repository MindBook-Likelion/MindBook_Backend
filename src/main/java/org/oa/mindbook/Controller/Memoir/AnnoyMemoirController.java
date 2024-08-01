package org.oa.mindbook.Controller.Memoir;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.Memoir.CreateAnnoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AnnoyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AnnoyMemoirResponseDto;
import org.oa.mindbook.Service.Memoir.AnnoyMemoirService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/annoyMemoir")
@Tag(name = "짜증 회고록 API", description = "짜증 회고록 관련 API입니다.")
public class AnnoyMemoirController {

    private final AnnoyMemoirService annoyMemoirService;

    @Operation(method = "POST", summary = "짜증 회고록 작성")
    @PostMapping("")
    public ResponseEntity<?> createAnnoyMemoir(@RequestBody CreateAnnoyMemoirRequestDto createAnnoyMemoirRequestDto) {
        log.info("오늘 있었던 일: {}", createAnnoyMemoirRequestDto.getMemory());
        log.info("느낀점: {}",createAnnoyMemoirRequestDto.getImpression());
        log.info("공개여부: {}", createAnnoyMemoirRequestDto.getStatus());

        Long AnnoyMemoirId = annoyMemoirService.saveAnnoyMemoir(createAnnoyMemoirRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(AnnoyMemoirId);
    }

    @Operation(method = "GET", summary = "짜증 회고록 상세 조회")
    @GetMapping("/detail")
    public AnnoyMemoirResponseDto getAnnoyMemoir(@RequestParam Long userId, @RequestParam Long annoyMemoirId) {
        return annoyMemoirService.getAnnoyMemoir(annoyMemoirId, userId);
    }

    @Operation(method = "GET", summary = "짜증 회고록 목록 작성")
    @GetMapping("")
    public List<AnnoyMemoirListResponseDto> getAnnoyMemoirList(@RequestParam String status, @RequestParam Long userId) {
        return annoyMemoirService.getAnnoyMemoirList(status, userId);
    }

}
