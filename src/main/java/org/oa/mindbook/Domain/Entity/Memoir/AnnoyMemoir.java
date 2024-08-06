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
@Table(name = "AnnoyMemoir")
public class AnnoyMemoir extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annoyMemoirId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String memory;

    private String status;

    @Builder
    public AnnoyMemoir(User user, String memory, String status) {
        this.user = user;
        this.memory = memory;
        this.status = status;
    }
}
