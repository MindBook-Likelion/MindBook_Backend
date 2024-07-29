package org.oa.mindbook.Service.Book;

import org.oa.mindbook.Dto.request.Book.BookReqDto;

public interface BookService {
    Long save(BookReqDto bookReqDto);
}
