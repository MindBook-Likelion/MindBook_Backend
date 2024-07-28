package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.MemoirComment.CreatePastMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.PastMemoirCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
