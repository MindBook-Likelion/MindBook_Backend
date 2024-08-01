package org.oa.mindbook.Controller.MemoirComment;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "화남 회고록 댓글 API", description = "화남 회고록 댓글 관련 API입니다.")
public class AngryMemoirCommentController {

    private final AngryMemoirCommentService angryMemoirCommentService;

    @Operation(method = "POST", summary = "화남 회고록 댓글 작성")
    @PostMapping("")
    public String createAngryMemoirComment(@RequestBody CreateAngryMemoirCommentRequestDto dto) {

        angryMemoirCommentService.saveAngryMemoirComment(dto);

        return "화남 회고록 댓글이 작성되었습니다.";

    }

    @Operation(method = "DELETE", summary = "화남 회고록 댓글 삭제")
    @DeleteMapping("/{angryMemoirCommentId}")
    public String deleteAngryMemoirComment(@PathVariable Long angryMemoirCommentId) {
        angryMemoirCommentService.deleteAngryMemoirComment(angryMemoirCommentId);

        return "화남 회고록 댓글이 삭제되었습니다.";
    }

    @Operation(method = "GET", summary = "화남 회고록 댓글 통계")
    @GetMapping("/count")
    public ResponseEntity<Long> countByAngryMemoirId(@RequestParam Long angryMemoirId) {
        List<AngryMemoirComment> comments = angryMemoirCommentService.getCommentByAngryMemoirId(angryMemoirId);
        return ResponseEntity.ok((long) comments.size());


    }
}
