package org.oa.mindbook.Dto.response.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.oa.mindbook.Domain.Entity.Book.Book;

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

    public BookResDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.pubdate = book.getPubdate();
        this.description = book.getDescription();
        this.image = book.getImage();
    }
}
