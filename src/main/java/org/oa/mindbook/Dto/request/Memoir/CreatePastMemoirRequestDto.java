package org.oa.mindbook.Dto.request.Memoir;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePastMemoirRequestDto {

    private Long userId;

    private String pastAt;

    private String memory;

    private String impression;

    private String status;

}
