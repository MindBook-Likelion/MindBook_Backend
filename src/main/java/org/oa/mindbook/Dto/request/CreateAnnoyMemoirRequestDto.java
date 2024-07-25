package org.oa.mindbook.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAnnoyMemoirRequestDto {

    private String userId;

    private String memory;

    private String impression;

    private String status;

    public AnnoyMemoir toEntity() {
        return AnnoyMemoir.builder()
                .userId(userId)
                .memory(memory)
                .impression(impression)
                .status(status)
                .build();
    }
}
