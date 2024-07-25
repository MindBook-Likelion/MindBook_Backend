package org.oa.mindbook.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnxietyMemoirResponseDto {

    private Long id;

    private String userId;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    public static AnxietyMemoirResponseDto of(AnxietyMemoir anxietyMemoir) {
        return AnxietyMemoirResponseDto.builder()
                .id(anxietyMemoir.getId())
                .userId(anxietyMemoir.getUserId())
                .createdAt(anxietyMemoir.getCreatedAt())
                .memory(anxietyMemoir.getMemory())
                .impression(anxietyMemoir.getImpression())
                .status(anxietyMemoir.getStatus())
                .build();
    }
}
