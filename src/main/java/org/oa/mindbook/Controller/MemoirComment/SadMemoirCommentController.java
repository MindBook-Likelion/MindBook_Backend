package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.MemoirComment.CreateSadMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.SadMemoirCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
