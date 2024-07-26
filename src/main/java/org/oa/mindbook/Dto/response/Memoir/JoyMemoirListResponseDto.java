package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoyMemoirListResponseDto {

    private Long joyMemoirId;

    private String createdAt;

    private String status;

    public static JoyMemoirListResponseDto of(JoyMemoir joyMemoir) {
        return JoyMemoirListResponseDto.builder()
                .joyMemoirId(joyMemoir.getJoyMemoirId())
                .createdAt(joyMemoir.getCreatedAt())
                .status(joyMemoir.getStatus())
                .build();
    }

}
