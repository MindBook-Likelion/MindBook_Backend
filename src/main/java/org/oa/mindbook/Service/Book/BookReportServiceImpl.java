package org.oa.mindbook.Service.Book;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Book.Book;
import org.oa.mindbook.Domain.Entity.Book.BookReport;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.Book.BookReportReqDto;
import org.oa.mindbook.Dto.response.Book.BookReportResDto;
import org.oa.mindbook.Repository.Book.BookReportRepository;
import org.oa.mindbook.Repository.Book.BookRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BookReportServiceImpl implements BookReportService {
    @Autowired
    BookReportRepository bookReportRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Long save(Long userId, BookReportReqDto bookReportReqDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));

        Book book = bookRepository.findById(bookReportReqDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Invaild book ID: " + bookReportReqDto.getBookId()));

        BookReport bookReport = BookReport.builder()
                .user(user)
                .book(book)
                .contents(bookReportReqDto.getContents())
                .build();

        BookReport savedBookReport = bookReportRepository.save(bookReport);
        return savedBookReport.getId();
    }

    public BookReportResDto getReportByBookIdAndUserEmail(Long bookId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Optional<BookReport> bookReportOptional = bookReportRepository.findByBookIdAndUserId(bookId, user.getId());

        if (bookReportOptional.isPresent()) {
            BookReport bookReport = bookReportOptional.get();
            return new BookReportResDto(bookReport, bookReport.getBook(), bookReport.getUser());
        } else {
            throw new IllegalArgumentException("Report not found");
        }
    }

    public void deleteReportByBookIdAndUserEmail(Long bookId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Optional<BookReport> bookReportOptional = bookReportRepository.findByBookIdAndUserId(bookId, user.getId());

        if (bookReportOptional.isPresent()) {
            bookReportRepository.deleteById(bookReportOptional.get().getId());
        } else {
            throw new IllegalArgumentException("Report not found");
        }
    }
}
