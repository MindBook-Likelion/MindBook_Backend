package org.oa.mindbook.Dto.request.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePastMemoirCommentRequestDto {

    private Long userId;

    private Long pastMemoirId;

    private String content;
}
