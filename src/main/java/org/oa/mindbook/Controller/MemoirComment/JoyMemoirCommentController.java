package org.oa.mindbook.Controller.MemoirComment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.MemoirComment.JoyMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateJoyMemoirCommentRequestDto;
import org.oa.mindbook.Service.MemoirComment.JoyMemoirCommentService;
import org.oa.mindbook.Service.User.UserService;
import org.oa.mindbook.auth.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/joyMemoirComment")
@Tag(name = "기쁨 회고록 댓글 API", description = "기쁨 회고록 댓글 관련 API입니다.")
public class JoyMemoirCommentController {

    private final JoyMemoirCommentService joyMemoirCommentService;
    private final UserService userService;

    @Operation(method = "POST", summary = "기쁨 회고록 댓글 작성")
    @PostMapping("")
    public String createJoyMemoirComment(@RequestBody CreateJoyMemoirCommentRequestDto dto,@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        String email = customUserDetails.getUsername();

        Long userId = userService.findUserIdByEmail(email);

        joyMemoirCommentService.saveJoyMemoirComment(dto, userId);

        return "기쁨 회고록 댓글이 작성되었습니다.";

    }

    @Operation(method = "DELETE", summary = "기쁨 회고록 댓글 삭제")
    @DeleteMapping("/{joyMemoirCommentId}")
    public String deleteJoyMemoirComment(@PathVariable Long joyMemoirCommentId) {
        joyMemoirCommentService.deleteJoyMemoirComment(joyMemoirCommentId);

        return "기쁨 회고록 댓글이 삭제되었습니다.";
    }

    @Operation(method = "GET", summary = "기쁨 회고록 댓글 통계")
    @GetMapping("/count")
    public ResponseEntity<Long> countByJoyMemoirId(@RequestParam Long joyMemoirId) {
        List<JoyMemoirComment> comments = joyMemoirCommentService.getCommentsByJoyMemoirId(joyMemoirId);
        return ResponseEntity.ok((long) comments.size());

    }
}
