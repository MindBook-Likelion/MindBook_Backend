package org.oa.mindbook.Dto.response.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.oa.mindbook.Domain.Entity.Book.Book;

@Getter
@Setter
@NoArgsConstructor
public class BookResDto {
    private String title;
    private String author;
    private String publisher;
    private String pubdate;
    private String description;
    private String isbn;
    private String image;

    public BookResDto(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.pubdate = book.getPubdate();
        this.description = book.getDescription();
        this.isbn = book.getIsbn();
        this.image = book.getImage();
    }
}
