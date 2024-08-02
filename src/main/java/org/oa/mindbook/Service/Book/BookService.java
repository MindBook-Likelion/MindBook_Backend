package org.oa.mindbook.Service.Book;

import org.oa.mindbook.Domain.Entity.Book.Book;
import org.oa.mindbook.Dto.request.Book.BookReqDto;
import org.oa.mindbook.Dto.response.Book.BookResDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Long save(BookReqDto bookReqDto);

    List<BookResDto> findAllByUserId(Long userId);

    boolean existsByUserIdAndTitle(Long userId, String title);

    Optional<Book> findByUserIdAndTitle(Long userId, String title);

    List<Book> findByUserIdAndTitleIgnoreSpaces(Long userId, String title);
}
