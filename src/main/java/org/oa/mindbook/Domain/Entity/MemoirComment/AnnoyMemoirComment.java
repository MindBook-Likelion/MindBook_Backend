package org.oa.mindbook.Domain.Entity.MemoirComment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.BaseTimeEntity;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AnnoyMemoirComment")
public class AnnoyMemoirComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annoyMemoirCommentId;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "annoyMemoirId", nullable = false)
    private AnnoyMemoir annoyMemoir;

    private String content;

}
