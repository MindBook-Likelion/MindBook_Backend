package org.oa.mindbook.Controller.MemoirComment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.SadMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateSadMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.SadMemoirCommentService;
import org.oa.mindbook.Service.User.UserService;
import org.oa.mindbook.auth.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/sadMemoirComment")
@Tag(name = "슬픔 회고록 댓글 API", description = "슬픔 회고록 댓글 관련 API입니다.")
public class SadMemoirCommentController {

    private final SadMemoirCommentService sadMemoirCommentService;
    private final UserService userService;

    @Operation(method = "POST", summary = "슬픔 회고록 댓글 작성")
    @PostMapping("")
    public String createSadMemoirComment(@RequestBody CreateSadMemoirCommentRequestDto dto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        sadMemoirCommentService.saveSadMemoirComment(dto, userId);

        return "슬픔 회고록 댓글이 작성되었습니다.";
    }

    @Operation(method = "DELETE", summary = "슬픔 회고록 댓글 삭제")
    @DeleteMapping("/{sadMemoirCommentId}")
    public String deleteSadMemoirComment(@PathVariable Long sadMemoirCommentId) {
        sadMemoirCommentService.deleteSadMemoirComment(sadMemoirCommentId);

        return "슬픔 회고록 댓글이 삭제되었습니다.";
    }

    @Operation(method = "GET", summary = "슬픔 회고록 댓글 통계")
    @GetMapping("/count")
    public ResponseEntity<Long> countBySadMemoirId(@RequestParam Long sadMemoirId) {
        List<SadMemoirComment> comments = sadMemoirCommentService.getCommentsBySadMemoirId(sadMemoirId);
        return ResponseEntity.ok((long) comments.size());
    }
}
