package org.oa.mindbook.Dto.request.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAngryMemoirCommentRequestDto {

    private Long userId;

    private Long angryMemoirId;

    private String content;

}
