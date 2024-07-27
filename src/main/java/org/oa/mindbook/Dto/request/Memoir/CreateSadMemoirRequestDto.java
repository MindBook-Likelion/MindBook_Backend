package org.oa.mindbook.Dto.request.Memoir;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSadMemoirRequestDto {

    private Long userId;

    private String memory;

    private String impression;

    private String status;

    public SadMemoir toEntity() {
        return SadMemoir.builder()
                .userId(userId)
                .memory(memory)
                .impression(impression)
                .status(status)
                .build();
    }
}
