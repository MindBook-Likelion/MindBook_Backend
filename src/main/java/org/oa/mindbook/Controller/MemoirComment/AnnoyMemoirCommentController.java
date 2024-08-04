package org.oa.mindbook.Controller.MemoirComment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnnoyMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnnoyMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.AnnoyMemoirCommentService;
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
@RequestMapping("/annoyMemoirComment")
@Tag(name = "짜증 회고록 댓글 API", description = "짜증 회고록 댓글 관련 API입니다.")
public class AnnoyMemoirCommentController {

    private final AnnoyMemoirCommentService annoyMemoirCommentService;
    private final UserService userService;

    @Operation(method = "POST", summary = "짜증 회고록 댓글 작성")
    @PostMapping("")
    public String createAnnoyMemoirComment(@RequestBody CreateAnnoyMemoirCommentRequestDto dto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        annoyMemoirCommentService.saveAnnoyMemoirComment(dto, userId);

        return "짜증 회고록 댓글이 작성되었습니다.";

    }

    @Operation(method = "DELETE", summary = "짜증 회고록 댓글 삭제")
    @DeleteMapping("/{annoyMemoirCommentId}")
    public String deleteAnnoyMemoirComment(@PathVariable Long annoyMemoirCommentId) {
        annoyMemoirCommentService.deleteAnnoyMemoirComment(annoyMemoirCommentId);

        return "짜증 회고록 댓글이 삭제되었습니다.";
    }

    @Operation(method = "GET", summary = "짜증 회고록 댓글 통계")
    @GetMapping("/count")
    public ResponseEntity<Long> countByAnnoyMemoirId(@RequestParam Long annoyMemoirId) {
        List<AnnoyMemoirComment> comments = annoyMemoirCommentService.getCommentsByAnnoyMemoirId(annoyMemoirId);
        return ResponseEntity.ok((long) comments.size());
    }
}
