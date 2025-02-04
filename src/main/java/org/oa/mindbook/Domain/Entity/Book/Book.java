package org.oa.mindbook.Domain.Entity.Book;

import jakarta.persistence.*;
import lombok.*;
import org.oa.mindbook.Domain.Entity.User.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "bookId")
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String publisher;

    @Column
    private String pubdate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
