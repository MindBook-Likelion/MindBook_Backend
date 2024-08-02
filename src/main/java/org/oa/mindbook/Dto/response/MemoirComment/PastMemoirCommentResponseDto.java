package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.MemoirComment.PastMemoirComment;
import org.oa.mindbook.Domain.Entity.User.User;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PastMemoirCommentResponseDto {

    private Long pastMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;

    public PastMemoirCommentResponseDto(PastMemoirComment pastMemoirComment, User user) {
        this.pastMemoirCommentId = pastMemoirComment.getPastMemoirCommentId();
        this.nickname = user.getNickName();
        this.content = pastMemoirComment.getContent();
        this.createdAt = pastMemoirComment.getCreatedAt();
    }
}
