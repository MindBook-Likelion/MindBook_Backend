package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AngryMemoirCommentResponseDto {

    private Long angryMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;
}
