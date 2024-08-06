package org.oa.mindbook.Dto.response.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.oa.mindbook.Domain.Entity.Book.Book;
import org.oa.mindbook.Domain.Entity.Book.BookReport;
import org.oa.mindbook.Domain.Entity.User.User;

@Getter
@Setter
@NoArgsConstructor
public class BookReportResDto {
    private Long bookReportId;
    private Long bookId;
    private String userNickname;
    private String contents;
    private String createdAt;

    public BookReportResDto(BookReport bookReport, Book book, User user) {
        this.bookReportId = bookReport.getId();
        this.bookId = book.getId();
        this.userNickname = user.getNickName();
        this.contents = bookReport.getContents();
        this.createdAt = bookReport.getCreatedAt().toString();
    }
}
