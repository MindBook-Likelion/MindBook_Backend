package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnxietyMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnxietyMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.AnxietyMemoirCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/anxietyMemoirComment")
public class AnxietyMemoirCommentController {

    private final AnxietyMemoirCommentService anxietyMemoirCommentService;
    @PostMapping("")
    public String createAnxietyMemoirComment(@RequestBody CreateAnxietyMemoirCommentRequestDto dto) {

        anxietyMemoirCommentService.saveAnxietyMemoirComment(dto);

        return "불안 회고록 댓글이 작성되었습니다.";

    }

    @DeleteMapping("/{anxietyMemoirCommentId}")
    public String deleteAnxietyMemoirComment(@PathVariable Long anxietyMemoirCommentId) {
        anxietyMemoirCommentService.deleteAnxietyMemoirComment(anxietyMemoirCommentId);

        return "불안 회고록 댓글이 삭제되었습니다.";
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countByAnxietyMemoirId(@RequestParam Long anxietyMemoirId) {
        List<AnxietyMemoirComment> comments = anxietyMemoirCommentService.getCommentsByAnxietyMemoirId(anxietyMemoirId);
        return ResponseEntity.ok((long) comments.size());
    }
}
