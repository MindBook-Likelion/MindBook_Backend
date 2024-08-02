package org.oa.mindbook.Dto.request.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookReportReqDto {
    private Long bookId;
    private String contents;
}
