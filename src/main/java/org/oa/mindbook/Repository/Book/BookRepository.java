package org.oa.mindbook.Repository.Book;

import org.oa.mindbook.Domain.Entity.Book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
