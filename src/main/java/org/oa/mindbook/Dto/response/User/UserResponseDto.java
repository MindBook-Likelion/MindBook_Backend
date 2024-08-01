package org.oa.mindbook.Dto.response.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.User.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserResponseDto {

    public Long id;

    public String nickName;

    public String email;

    //User Entity -> ResponseDto 변환 메서드
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .build();
    }
}