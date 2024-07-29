package org.oa.mindbook.Service.Book;

import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.request.Book.BookReqDto;
import org.oa.mindbook.Dto.response.Book.BookResDto;

import java.util.List;

public interface BookService {
    Long save(BookReqDto bookReqDto);

    List<BookResDto> findAllByUserId(Long userId);
}
