package org.oa.mindbook.Service.Book;

import org.oa.mindbook.Dto.request.Book.BookReportReqDto;
import org.oa.mindbook.Dto.response.Book.BookReportResDto;

public interface BookReportService {
    Long save(BookReportReqDto bookReportReqDto);

    BookReportResDto getReportById(Long id);

    void deleteReportById(Long id);
}
