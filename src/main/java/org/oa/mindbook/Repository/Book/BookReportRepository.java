package org.oa.mindbook.Repository.Book;

import org.oa.mindbook.Domain.Entity.Book.BookReport;
import org.oa.mindbook.Domain.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookReportRepository extends JpaRepository<BookReport, Long> {
    void deleteAllByUser(User user);

    Optional<BookReport> findByBookIdAndUserId(Long bookId, Long userId);
}