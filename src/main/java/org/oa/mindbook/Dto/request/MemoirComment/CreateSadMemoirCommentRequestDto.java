package org.oa.mindbook.Dto.request.MemoirComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSadMemoirCommentRequestDto {

    private Long userId;

    private  Long sadMemoirId;

    private String content;
}
