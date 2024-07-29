package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AngryMemoirListResponseDto {

    private Long angryMemoirId;

    private String createdAt;

    private String status;

    public static AngryMemoirListResponseDto of(AngryMemoir angryMemoir) {
        return AngryMemoirListResponseDto.builder()
                .angryMemoirId(angryMemoir.getAngryMemoirId())
                .createdAt(angryMemoir.getCreatedAt())
                .status(angryMemoir.getStatus())
                .build();
    }
}
