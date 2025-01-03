package org.oa.mindbook.Service.Book;

import org.oa.mindbook.Dto.request.Book.BookReportReqDto;
import org.oa.mindbook.Dto.response.Book.BookReportResDto;

public interface BookReportService {
    Long save(Long userId, BookReportReqDto bookReportReqDto);

    BookReportResDto getReportByBookIdAndUserEmail(Long bookId, String email);

    void deleteReportByBookIdAndUserEmail(Long bookId, String email);
}
