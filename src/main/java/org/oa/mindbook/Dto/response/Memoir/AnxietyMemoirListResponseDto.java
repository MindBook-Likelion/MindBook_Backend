package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnxietyMemoirListResponseDto {

    private Long anxietyMemoirId;

    private String createdAt;

    private String status;

    public static AnxietyMemoirListResponseDto of(AnxietyMemoir anxietyMemoir) {
        return AnxietyMemoirListResponseDto.builder()
                .anxietyMemoirId(anxietyMemoir.getAnxietyMemoirId())
                .createdAt(anxietyMemoir.getCreatedAt())
                .status(anxietyMemoir.getStatus())
                .build();
    }
}
