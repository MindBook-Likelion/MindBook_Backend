package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Dto.response.MemoirComment.AnxietyMemoirCommentResponseDto;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnxietyMemoirResponseDto {

    private Long anxietyMemoirId;

    private Long userId;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    private List<AnxietyMemoirCommentResponseDto> commentList;

    public static AnxietyMemoirResponseDto of(AnxietyMemoir anxietyMemoir, List<AnxietyMemoirCommentResponseDto> commentList) {
        return AnxietyMemoirResponseDto.builder()
                .anxietyMemoirId(anxietyMemoir.getAnxietyMemoirId())
                .userId(anxietyMemoir.getUserId())
                .createdAt(anxietyMemoir.getCreatedAt())
                .memory(anxietyMemoir.getMemory())
                .impression(anxietyMemoir.getImpression())
                .status(anxietyMemoir.getStatus())
                .commentList(commentList)
                .build();
    }
}
