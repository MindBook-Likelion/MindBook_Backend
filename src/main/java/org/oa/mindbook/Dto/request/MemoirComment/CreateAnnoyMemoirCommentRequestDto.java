package org.oa.mindbook.Dto.request.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnnoyMemoirCommentRequestDto {

    private Long userId;

    private Long annoyMemoirId;

    private String content;
}
