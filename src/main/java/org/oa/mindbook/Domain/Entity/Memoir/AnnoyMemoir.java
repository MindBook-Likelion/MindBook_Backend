package org.oa.mindbook.Domain.Entity.Memoir;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "AnnoyMemoir")
public class AnnoyMemoir extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annoyMemoirId;

    private String userId;

    @Column(columnDefinition = "TEXT")
    private String memory;

    @Column(columnDefinition = "TEXT")
    private String impression;

    private String status;

    @Builder
    public AnnoyMemoir(String userId, String memory, String impression, String status) {
        this.userId = userId;
        this.memory = memory;
        this.impression = impression;
        this.status = status;
    }
}
