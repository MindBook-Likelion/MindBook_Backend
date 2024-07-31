package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.SadMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateSadMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.SadMemoirCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/sadMemoirComment")
public class SadMemoirCommentController {

    private final SadMemoirCommentService sadMemoirCommentService;
    @PostMapping("")
    public String createSadMemoirComment(@RequestBody CreateSadMemoirCommentRequestDto dto) {

        sadMemoirCommentService.saveSadMemoirComment(dto);

        return "슬픔 회고록 댓글이 작성되었습니다.";
    }

    @DeleteMapping("/{sadMemoirCommentId}")
    public String deleteSadMemoirComment(@PathVariable Long sadMemoirCommentId) {
        sadMemoirCommentService.deleteSadMemoirComment(sadMemoirCommentId);

        return "슬픔 회고록 댓글이 삭제되었습니다.";
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countBySadMemoirId(@RequestParam Long sadMemoirId) {
        List<SadMemoirComment> comments = sadMemoirCommentService.getCommentsBySadMemoirId(sadMemoirId);
        return ResponseEntity.ok((long) comments.size());
    }
}
