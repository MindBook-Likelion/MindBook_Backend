package org.oa.mindbook.Dto.request.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.Entity.User.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Getter
public class CreateUserRequestDto {

    public String nickName;

    public String email;

    public String password;

    //User Dto -> User Entity로 변환하는 메서드
    public User toEntity(PasswordEncoder passwordEncoder) {
        //Password Encoder 통해 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        return User.builder()
                .nickName(nickName)
                .email(email)
                .password(encodedPassword) //암호화된 비밀번호 저장
                .roles("USER")
                .build();
    }

}