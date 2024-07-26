package org.oa.mindbook.Dto.response.Memoir;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoyMemoirResponseDto {

    private Long joyMemoirId;

    private String userId;

    private String createdAt;

    private String memory;

    private String impression;

    private String status;

    public static JoyMemoirResponseDto of(JoyMemoir joyMemoir) {
        return JoyMemoirResponseDto.builder()
                .joyMemoirId(joyMemoir.getJoyMemoirId())
                .userId(joyMemoir.getUserId())
                .createdAt(joyMemoir.getCreatedAt())
                .memory(joyMemoir.getMemory())
                .impression(joyMemoir.getImpression())
                .status(joyMemoir.getStatus())
                .build();
    }





}
