package org.oa.mindbook.Dto.request.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateJoyMemoirCommentRequestDto {

    private Long userId;

    private Long joyMemoirId;

    private String content;

}
