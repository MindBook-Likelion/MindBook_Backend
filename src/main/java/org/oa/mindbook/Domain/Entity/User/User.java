package org.oa.mindbook.Domain.Entity.User;

import jakarta.persistence.*;
import lombok.*;
import org.oa.mindbook.Domain.BaseTimeEntity;
import org.oa.mindbook.Dto.request.User.UpdateUserRequestDto;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String profileImage;

    @Column
    private String roles;

    @Column
    private UserStatus status;

    // Dto를 사용해서 update하는 메서드.
    // Entity에는 Setter를 사용하지 않는 것이 좋음
    public void update(UpdateUserRequestDto userRequestDto) {
        if (userRequestDto.getPassword() != null && !userRequestDto.getPassword().isEmpty()) {
            this.password = userRequestDto.getPassword();
        }
    }

    public User encodePassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(password);
        return this;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
        return passwordEncoder.matches(checkPassword, getPassword());
    }
}
