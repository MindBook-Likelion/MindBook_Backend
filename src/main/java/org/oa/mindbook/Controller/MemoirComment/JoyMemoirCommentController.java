package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.MemoirComment.CreateJoyMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.JoyMemoirCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/joyMemoirComment")
public class JoyMemoirCommentController {

    private final JoyMemoirCommentService joyMemoirCommentService;

    @PostMapping("")
    public String createJoyMemoir(@RequestBody CreateJoyMemoirCommentRequestDto dto) {

        log.info(dto.getContent());

        joyMemoirCommentService.saveJoyMemoirComment(dto);

        return "기쁨 회고록 댓글이 작성되었습니다.";

    }
}
