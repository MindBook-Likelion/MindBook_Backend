package org.oa.mindbook.Dto.request.Memoir;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAnxietyMemoirRequestDto {

    private Long userId;

    private String memory;

    private String impression;

    private String status;

    public AnxietyMemoir toEntity() {
        return AnxietyMemoir.builder()
                .userId(userId)
                .memory(memory)
                .impression(impression)
                .status(status)
                .build();
    }
}
