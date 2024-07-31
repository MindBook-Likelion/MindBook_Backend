package org.oa.mindbook.Dto.request.Memoir;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSadMemoirRequestDto {

    private Long userId;

    private String memory;

    private String impression;

    private String status;

}
