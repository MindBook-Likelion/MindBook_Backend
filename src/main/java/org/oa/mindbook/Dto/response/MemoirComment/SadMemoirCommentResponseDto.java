package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SadMemoirCommentResponseDto {

    private Long sadMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;
}
