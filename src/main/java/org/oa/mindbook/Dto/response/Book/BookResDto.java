package org.oa.mindbook.Dto.response.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResDto {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String pubdate;
    private String description;
    private String isbn;
    private String image;
    @Builder
    public BookResDto(String title, String author, String publisher, String pubdate, String description, String isbn, String imageURL) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.pubdate = pubdate;
        this.description = description;
        this.isbn = isbn;
        this.image = image;
    }
}
