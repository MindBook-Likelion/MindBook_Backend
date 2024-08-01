package org.oa.mindbook.Domain.Entity.MemoirComment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.oa.mindbook.Domain.BaseTimeEntity;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Domain.Entity.User;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AnxietyMemoirComment")
public class AnxietyMemoirComment  extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long anxietyMemoirCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anxietyMemoirId", nullable = false)
    private AnxietyMemoir anxietyMemoir;

    private String content;
}
