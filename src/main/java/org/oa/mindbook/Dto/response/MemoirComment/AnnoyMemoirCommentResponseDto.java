package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnnoyMemoirCommentResponseDto {

    private Long annoyMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;
}
