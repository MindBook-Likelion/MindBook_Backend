package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.MemoirComment.SadMemoirComment;
import org.oa.mindbook.Domain.Entity.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SadMemoirCommentResponseDto {

    private Long sadMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;

    public SadMemoirCommentResponseDto(SadMemoirComment sadMemoirComment, User user) {
        this.sadMemoirCommentId = sadMemoirComment.getSadMemoirCommentId();
        this.nickname = user.getNickName();
        this.content = sadMemoirComment.getContent();
        this.createdAt = sadMemoirComment.getCreatedAt();
    }
}
