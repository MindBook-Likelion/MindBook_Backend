package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnxietyMemoirComment;
import org.oa.mindbook.Domain.Entity.User.User;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnxietyMemoirCommentResponseDto {

    private Long anxietyMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;

    public AnxietyMemoirCommentResponseDto(AnxietyMemoirComment anxietyMemoirComment, User user) {
        this.anxietyMemoirCommentId = anxietyMemoirComment.getAnxietyMemoirCommentId();
        this.nickname = user.getNickName();
        this.content = anxietyMemoirComment.getContent();
        this.createdAt = anxietyMemoirComment.getCreatedAt();
    }

}
