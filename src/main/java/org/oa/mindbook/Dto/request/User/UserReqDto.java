package org.oa.mindbook.Dto.request.User;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserReqDto {
    private String nickName;
    private String userId;
    private String password;
    private String profileImage;
}
