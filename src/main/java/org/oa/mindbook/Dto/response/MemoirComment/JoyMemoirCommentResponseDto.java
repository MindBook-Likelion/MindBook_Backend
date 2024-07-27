package org.oa.mindbook.Dto.response.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoyMemoirCommentResponseDto {

    private Long joyMemoirCommentId;

    private String nickname;

    private String content;

    private String createdAt;

}
