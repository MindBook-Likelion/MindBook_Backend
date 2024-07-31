package org.oa.mindbook.Controller.MemoirComment;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.AngryMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAngryMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.AngryMemoirCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/angryMemoirComment")
public class AngryMemoirCommentController {

    private final AngryMemoirCommentService angryMemoirCommentService;

    @PostMapping("")
    public String createAngryMemoirComment(@RequestBody CreateAngryMemoirCommentRequestDto dto) {

        angryMemoirCommentService.saveAngryMemoirComment(dto);

        return "화남 회고록 댓글이 작성되었습니다.";

    }

    @DeleteMapping("/{angryMemoirCommentId}")
    public String deleteAngryMemoirComment(@PathVariable Long angryMemoirCommentId) {
        angryMemoirCommentService.deleteAngryMemoirComment(angryMemoirCommentId);

        return "화남 회고록 댓글이 삭제되었습니다.";
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countByAngryMemoirId(@RequestParam Long angryMemoirId) {
        List<AngryMemoirComment> comments = angryMemoirCommentService.getCommentByAngryMemoirId(angryMemoirId);
        return ResponseEntity.ok((long) comments.size());


    }
}
