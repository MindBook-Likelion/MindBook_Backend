package org.oa.mindbook.Controller.MemoirComment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.PastMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreatePastMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.PastMemoirCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pastMemoirComment")
@Tag(name = "추억 회고록 댓글 API", description = "추억 회고록 댓글 관련 API입니다.")
public class PastMemoirCommentController {

    private final PastMemoirCommentService pastMemoirCommentService;
    @Operation(method = "POST", summary = "추억 회고록 댓글 작성")
    @PostMapping("")
    public String createPastMemoirComment(@RequestBody CreatePastMemoirCommentRequestDto dto) {

        pastMemoirCommentService.savePastMemoirComment(dto);

        return "추억 회고록 댓글이 생성되었습니다.";

    }

    @Operation(method = "DELETE", summary = "추억 회고록 댓글 삭제")
    @DeleteMapping("/{pastMemoirCommentId}")
    public String deletePastMemoirComment(@PathVariable Long pastMemoirCommentId) {
        pastMemoirCommentService.deletePastMemoirComment(pastMemoirCommentId);

        return "추억 회고록 댓글이 삭제되었습니다.";
    }

    @Operation(method = "GET", summary = "추억 회고록 댓글 통계")
    @GetMapping("/count")
    public ResponseEntity<Long> countByPastMemoirId(@RequestParam Long pastMemoirId) {
        List<PastMemoirComment> comments = pastMemoirCommentService.getCommentsByPastMemoirId(pastMemoirId);
        return ResponseEntity.ok((long) comments.size());
    }
}
