package org.oa.mindbook.Dto.request.Memoir;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePastMemoirRequestDto {

    private Long userId;

    private String pastAt;

    private String memory;

    private String status;

}
