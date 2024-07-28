package org.oa.mindbook.Controller.MemoirComment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnnoyMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.AnnoyMemoirCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
