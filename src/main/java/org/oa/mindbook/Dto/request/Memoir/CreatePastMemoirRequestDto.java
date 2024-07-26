package org.oa.mindbook.Dto.request.Memoir;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePastMemoirRequestDto {

    private String userId;

    private String pastAt;

    private String memory;

    private String impression;

    private String status;

    public PastMemoir toEntity() {
        return PastMemoir.builder()
                .userId(userId)
                .pastAt(pastAt)
                .memory(memory)
                .impression(impression)
                .status(status)
                .build();
    }
}
