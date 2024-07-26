package org.oa.mindbook.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SadMemoirResponseDto {

    private Long id;

    private String userId;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    public static SadMemoirResponseDto of(SadMemoir sadMemoir) {
        return SadMemoirResponseDto.builder()
                .id(sadMemoir.getId())
                .userId(sadMemoir.getUserId())
                .createdAt(sadMemoir.getCreatedAt())
                .memory(sadMemoir.getMemory())
                .impression(sadMemoir.getImpression())
                .status(sadMemoir.getStatus())
                .build();
    }
}
