package org.oa.mindbook.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AngryMemoirResponseDto {

    private Long id;

    private String userId;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    public static AngryMemoirResponseDto of(AngryMemoir angryMemoir) {
        return AngryMemoirResponseDto.builder()
                .id(angryMemoir.getId())
                .userId(angryMemoir.getUserId())
                .createdAt(angryMemoir.getCreatedAt())
                .memory(angryMemoir.getMemory())
                .impression(angryMemoir.getImpression())
                .status(angryMemoir.getStatus())
                .build();
    }
}
