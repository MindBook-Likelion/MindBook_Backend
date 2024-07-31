package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.JoyMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateJoyMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.JoyMemoirCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/joyMemoirComment")
public class JoyMemoirCommentController {

    private final JoyMemoirCommentService joyMemoirCommentService;

    @PostMapping("")
    public String createJoyMemoirComment(@RequestBody CreateJoyMemoirCommentRequestDto dto) {

        joyMemoirCommentService.saveJoyMemoirComment(dto);

        return "기쁨 회고록 댓글이 작성되었습니다.";

    }

    @DeleteMapping("/{joyMemoirCommentId}")
    public String deleteJoyMemoirComment(@PathVariable Long joyMemoirCommentId) {
        joyMemoirCommentService.deleteJoyMemoirComment(joyMemoirCommentId);

        return "기쁨 회고록 댓글이 삭제되었습니다.";
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countByJoyMemoirId(@RequestParam Long joyMemoirId) {
        List<JoyMemoirComment> comments = joyMemoirCommentService.getCommentsByJoyMemoirId(joyMemoirId);
        return  ResponseEntity.ok((long) comments.size());

    }
}
