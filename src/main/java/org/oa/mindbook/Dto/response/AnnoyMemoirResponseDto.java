package org.oa.mindbook.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnoyMemoirResponseDto {

    private Long annoyMemoirId;

    private String userId;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    public static AnnoyMemoirResponseDto of(AnnoyMemoir annoyMemoir) {
        return AnnoyMemoirResponseDto.builder()
                .annoyMemoirId(annoyMemoir.getAnnoyMemoirId())
                .userId(annoyMemoir.getUserId())
                .createdAt(annoyMemoir.getCreatedAt())
                .memory(annoyMemoir.getMemory())
                .impression(annoyMemoir.getImpression())
                .status(annoyMemoir.getStatus())
                .build();
    }
}
