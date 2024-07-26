package org.oa.mindbook.Domain.Entity.Memoir;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.BaseTimeEntity;

import javax.management.monitor.StringMonitor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "JoyMemoir")
public class JoyMemoir extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long joyMemoirId;

    private String userId;

    @Column(columnDefinition = "TEXT")
    private String memory;

    @Column(columnDefinition = "TEXT")
    private String impression;

    private String status;

    @Builder
    public JoyMemoir(String userId, String memory,String impression, String status) {
        this.userId = userId;
        this.memory = memory;
        this.impression = impression;
        this.status = status;
    }


}
