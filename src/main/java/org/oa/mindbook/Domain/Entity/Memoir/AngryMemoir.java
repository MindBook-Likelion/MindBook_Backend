package org.oa.mindbook.Domain.Entity.Memoir;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.BaseTimeEntity;
import org.oa.mindbook.Domain.Entity.User.User;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "AngryMemoir")
public class AngryMemoir extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long angryMemoirId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String memory;

    @Column(columnDefinition = "TEXT")
    private String impression;

    private String status;

    @Builder
    public AngryMemoir(User user, String memory, String impression, String status) {
        this.user = user;
        this.memory = memory;
        this.impression = impression;
        this.status = status;
    }
}
