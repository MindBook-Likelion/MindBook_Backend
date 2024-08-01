package org.oa.mindbook.Service.Book;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Book.Book;
import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.request.Book.BookReqDto;
import org.oa.mindbook.Dto.response.Book.BookResDto;
import org.oa.mindbook.Repository.Book.BookRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Long save(BookReqDto bookReqDto) {
        User user = userRepository.findById(bookReqDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + bookReqDto.getUserId()));

        Book book = Book.builder()
                .user(user)
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

    @Override
    public List<BookResDto> findAllByUserId(Long userId) {
        List<Book> getBook = bookRepository.findAllByUserId(userId);

        List<BookResDto> bookResDtoList = getBook.stream()
                .map(book -> new BookResDto(book))
                .collect(Collectors.toList());

        return bookResDtoList;
    }

    @Override
    public boolean existsByUserIdAndTitle(Long userId, String title) {
        return bookRepository.findByUserIdAndTitle(userId, title).isPresent();
    }

    @Override
    public Optional<Book> findByUserIdAndTitle(Long userId, String title) {
        return bookRepository.findByUserIdAndTitle(userId, title);
    }

    @Override
    public List<Book> findByUserIdAndTitleIgnoreSpaces(Long userId, String title) {
        return bookRepository.findByUserIdAndTitleIgnoreSpaces(userId, title.replaceAll("\\s+", ""));
    }
}
