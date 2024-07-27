package org.oa.mindbook.Domain.Entity.Memoir;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
public class SadMemoir extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sadMemoirId;

    private Long userId;

    @Column(columnDefinition = "TEXT")
    private String memory;

    @Column(columnDefinition = "TEXT")
    private String impression;

    private String status;

    @Builder
    public SadMemoir(Long userId, String memory, String impression, String status) {
        this.userId = userId;
        this.memory = memory;
        this.impression = impression;
        this.status = status;
    }
}
