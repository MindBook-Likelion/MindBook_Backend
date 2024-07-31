package org.oa.mindbook.Dto.request.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAnxietyMemoirCommentRequestDto {

    private Long userId;

    private Long anxietyMemoirId;

    private String content;
}
