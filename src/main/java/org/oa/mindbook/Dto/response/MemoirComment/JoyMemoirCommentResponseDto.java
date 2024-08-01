package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.MemoirComment.JoyMemoirComment;
import org.oa.mindbook.Domain.Entity.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoyMemoirCommentResponseDto {

    private Long joyMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;

    public JoyMemoirCommentResponseDto(JoyMemoirComment joyMemoirComment, User user) {
        this.joyMemoirCommentId = joyMemoirComment.getJoyMemoirCommentId();
        this.nickname = user.getNickName();
        this.content = joyMemoirComment.getContent();
        this.createdAt = joyMemoirComment.getCreatedAt();
    }

}
