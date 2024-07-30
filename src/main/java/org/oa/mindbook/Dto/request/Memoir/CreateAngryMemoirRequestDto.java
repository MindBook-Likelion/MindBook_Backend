package org.oa.mindbook.Dto.request.Memoir;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateAngryMemoirRequestDto {

    private Long userId;

    private String memory;

    private String impression;

    private String status;

}
