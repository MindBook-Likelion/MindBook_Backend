package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.MemoirComment.AngryMemoirComment;
import org.oa.mindbook.Domain.Entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AngryMemoirCommentResponseDto {

    private Long angryMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;

    public AngryMemoirCommentResponseDto(AngryMemoirComment angryMemoirComment, User user) {
        this.angryMemoirCommentId = angryMemoirComment.getAngryMemoirCommentId();
        this.nickname = user.getNickName();
        this.content = angryMemoirComment.getContent();
        this.createdAt = angryMemoirComment.getCreatedAt();
    }
}
