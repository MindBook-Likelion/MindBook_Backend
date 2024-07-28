package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnxietyMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.AnxietyMemoirCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
