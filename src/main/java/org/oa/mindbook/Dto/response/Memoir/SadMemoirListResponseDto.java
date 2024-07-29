package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SadMemoirListResponseDto {

    private Long sadMemoirId;

    private String createdAt;

    private String status;


    public static SadMemoirListResponseDto of(SadMemoir sadMemoir) {
        return SadMemoirListResponseDto.builder()
                .sadMemoirId(sadMemoir.getSadMemoirId())
                .createdAt(sadMemoir.getCreatedAt())
                .status(sadMemoir.getStatus())
                .build();
    }
}
