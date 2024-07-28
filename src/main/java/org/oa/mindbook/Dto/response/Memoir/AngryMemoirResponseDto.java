package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Dto.response.MemoirComment.AngryMemoirCommentResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AngryMemoirResponseDto {

    private Long angryMemoirId;

    private Long userId;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    private List<AngryMemoirCommentResponseDto> commentList;

    public static AngryMemoirResponseDto of(AngryMemoir angryMemoir, List<AngryMemoirCommentResponseDto> commentList) {
        return AngryMemoirResponseDto.builder()
                .angryMemoirId(angryMemoir.getAngryMemoirId())
                .userId(angryMemoir.getUserId())
                .createdAt(angryMemoir.getCreatedAt())
                .memory(angryMemoir.getMemory())
                .impression(angryMemoir.getImpression())
                .status(angryMemoir.getStatus())
                .commentList(commentList)
                .build();
    }
}
