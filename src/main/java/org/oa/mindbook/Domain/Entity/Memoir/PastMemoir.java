package org.oa.mindbook.Domain.Entity.Memoir;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
public class PastMemoir extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pastMemoirId;

    private Long userId;

    private String pastAt;

    @Column(columnDefinition = "TEXT")
    private String memory;

    @Column(columnDefinition = "TEXT")
    private String impression;

    private String status;

    @Builder
    public PastMemoir(Long userId, String pastAt, String memory, String impression, String status) {
        this.userId = userId;
        this.pastAt = pastAt;
        this.memory = memory;
        this.impression = impression;
        this.status = status;
    }
}
