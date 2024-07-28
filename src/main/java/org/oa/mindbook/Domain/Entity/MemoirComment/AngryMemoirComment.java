package org.oa.mindbook.Domain.Entity.MemoirComment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.BaseTimeEntity;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AngryMemoirComment")
public class AngryMemoirComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long angryMemoirCommentId;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "angryMemoirId", nullable = false)
    private AngryMemoir angryMemoir;

    private String content;
}
