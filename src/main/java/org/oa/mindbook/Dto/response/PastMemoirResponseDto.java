package org.oa.mindbook.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.oa.mindbook.Repository.Memoir.PastMemoirRepository;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PastMemoirResponseDto {

    private Long id;

    private String userId;

    private String createdAt;

    private String pastAt;

    private String memory;

    private String impression;

    private String status;

    public static PastMemoirResponseDto of(PastMemoir pastMemoir) {
        return PastMemoirResponseDto.builder()
                .id(pastMemoir.getId())
                .userId(pastMemoir.getUserId())
                .createdAt(pastMemoir.getCreatedAt())
                .pastAt(pastMemoir.getPastAt())
                .memory(pastMemoir.getMemory())
                .impression(pastMemoir.getImpression())
                .status(pastMemoir.getStatus())
                .build();
    }
}
