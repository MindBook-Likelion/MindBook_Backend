package org.oa.mindbook.Repository.Book;

import org.oa.mindbook.Domain.Entity.Book.Book;
import org.oa.mindbook.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByUserId(Long userId);
    Optional<Book> findByUserIdAndTitle(Long userId, String title);

    @Query("SELECT b FROM Book b WHERE b.user.id = :userId AND REPLACE(b.title, ' ', '') LIKE %:title%")
    List<Book> findByUserIdAndTitleIgnoreSpaces(@Param("userId") Long userId, @Param("title") String title);
}
