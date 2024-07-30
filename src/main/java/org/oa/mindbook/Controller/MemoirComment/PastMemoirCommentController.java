package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.MemoirComment.CreatePastMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.PastMemoirCommentService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pastMemoirComment")
public class PastMemoirCommentController {

    private final PastMemoirCommentService pastMemoirCommentService;
    @PostMapping("")
    public String createPastMemoirComment(@RequestBody CreatePastMemoirCommentRequestDto dto) {

        pastMemoirCommentService.savePastMemoirComment(dto);

        return "추억 회고록 댓글이 생성되었습니다.";

    }

    @DeleteMapping("/{pastMemoirCommentId}")
    public String deletePastMemoirComment(@PathVariable Long pastMemoirCommentId) {
        pastMemoirCommentService.deletePastMemoirComment(pastMemoirCommentId);

        return "추억 회고록 댓글이 삭제되었습니다.";
    }
}
