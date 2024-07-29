package org.oa.mindbook.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.oa.mindbook.Domain.BaseTimeEntity;

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
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column
    private String profileImage;
}
