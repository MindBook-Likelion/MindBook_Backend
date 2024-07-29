package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnnoyMemoirListResponseDto {

    private Long annoyMemoirId;

    private String createdAt;

    private String status;

    public static AnnoyMemoirListResponseDto of(AnnoyMemoir annoyMemoir) {
        return AnnoyMemoirListResponseDto.builder()
                .annoyMemoirId(annoyMemoir.getAnnoyMemoirId())
                .createdAt(annoyMemoir.getCreatedAt())
                .status(annoyMemoir.getStatus())
                .build();
    }
}
