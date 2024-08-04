package org.oa.mindbook.Dto.request.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String publisher;
    private String pubdate;
    private String description;
    private String image;

    @Builder
    public BookDto(String title, String author, String publisher, String pubdate, String description, String image) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.description = description;
        this.image = image;
    }
}