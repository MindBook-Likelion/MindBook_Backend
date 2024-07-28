package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PastMemoirCommentResponseDto {

    private Long pastMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;
}
