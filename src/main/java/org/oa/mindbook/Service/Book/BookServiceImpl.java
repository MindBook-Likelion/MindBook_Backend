package org.oa.mindbook.Service.Book;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Book.Book;
import org.oa.mindbook.Dto.request.Book.BookReqDto;
import org.oa.mindbook.Repository.Book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Long save(BookReqDto bookReqDto) {
            Book book = Book.builder()
                    .id(bookReqDto.getId())
                    .title(bookReqDto.getTitle())
                    .author(bookReqDto.getAuthor())
                    .publisher(bookReqDto.getPublisher())
                    .pubdate(bookReqDto.getPubdate())
                    .description(bookReqDto.getDescription())
                    .isbn(bookReqDto.getIsbn())
                    .image(bookReqDto.getImage())
                    .build();

            Book savedBook = bookRepository.save(book);
            return savedBook.getId();
    }
}
