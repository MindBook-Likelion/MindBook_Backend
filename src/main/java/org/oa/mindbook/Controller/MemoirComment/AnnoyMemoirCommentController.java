package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnnoyMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnnoyMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.AnnoyMemoirCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/annoyMemoirComment")
public class AnnoyMemoirCommentController {

    private final AnnoyMemoirCommentService annoyMemoirCommentService;

    @PostMapping("")
    public String createAnnoyMemoirComment(@RequestBody CreateAnnoyMemoirCommentRequestDto dto) {

        annoyMemoirCommentService.saveAnnoyMemoirComment(dto);

        return "짜증 회고록 댓글이 작성되었습니다.";

    }

    @DeleteMapping("/{annoyMemoirCommentId}")
    public String deleteAnnoyMemoirComment(@PathVariable Long annoyMemoirCommentId) {
        annoyMemoirCommentService.deleteAnnoyMemoirComment(annoyMemoirCommentId);

        return "짜증 회고록 댓글이 삭제되었습니다.";
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countByAnnoyMemoirId(@RequestParam Long annoyMemoirId) {
        List<AnnoyMemoirComment> comments = annoyMemoirCommentService.getCommentsByAnnoyMemoirId(annoyMemoirId);
        return ResponseEntity.ok((long) comments.size());
    }
}
