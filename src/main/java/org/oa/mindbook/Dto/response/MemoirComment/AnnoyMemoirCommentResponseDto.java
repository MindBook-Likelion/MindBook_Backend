package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnnoyMemoirComment;
import org.oa.mindbook.Domain.Entity.User;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnoyMemoirCommentResponseDto {

    private Long annoyMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;

    public AnnoyMemoirCommentResponseDto(AnnoyMemoirComment annoyMemoirComment, User user) {
        this.annoyMemoirCommentId = annoyMemoirComment.getAnnoyMemoirCommentId();
        this.nickname = user.getNickName();
        this.content =  annoyMemoirComment.getContent();
        this.createdAt = annoyMemoirComment.getCreatedAt();
    }
}
