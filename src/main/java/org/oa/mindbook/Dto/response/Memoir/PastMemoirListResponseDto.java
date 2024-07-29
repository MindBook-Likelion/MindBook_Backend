package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PastMemoirListResponseDto {

    private Long pastMemoirId;

    private String createdAt;

    private String status;

    public static PastMemoirListResponseDto of(PastMemoir pastMemoir) {
        return PastMemoirListResponseDto.builder()
                .pastMemoirId(pastMemoir.getPastMemoirId())
                .createdAt(pastMemoir.getCreatedAt())
                .status(pastMemoir.getCreatedAt())
                .build();
    }
}
