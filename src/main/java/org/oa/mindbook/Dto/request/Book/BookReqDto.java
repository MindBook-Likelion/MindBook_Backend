package org.oa.mindbook.Dto.request.Book;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookReqDto {
    private String title;
    private String author;
    private String publisher;
    private String pubdate;
    private String description;
    private String isbn;
    private String image;
}
