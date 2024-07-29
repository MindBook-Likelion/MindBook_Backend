package org.oa.mindbook.Controller.Book;

import lombok.RequiredArgsConstructor;
import org.oa.mindbook.Domain.Entity.Book.Book;
import org.oa.mindbook.Dto.request.Book.BookReqDto;
import org.oa.mindbook.Dto.response.Book.BookResDto;
import org.oa.mindbook.Service.Book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<?> saveBook(@RequestBody BookReqDto bookReqDto) {
        try {
            // 책이 이미 존재하는지 확인
            if (bookService.existsByUserIdAndTitle(bookReqDto.getUserId(), bookReqDto.getTitle())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 추가된 책입니다.");
            }

            // 책을 저장
            Long bookId = bookService.save(bookReqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/myBook")
    List<BookResDto> getBookList(@RequestParam Long userId) {
        List<BookResDto> bookResDto = bookService.findAllByUserId(userId);

        return bookResDto;
    }

    @GetMapping("/myBook/search")
    public ResponseEntity<?> searchBook(@RequestParam Long userId, @RequestParam String title) {
        List<Book> books = bookService.findByUserIdAndTitleIgnoreSpaces(userId, title);
        if (!books.isEmpty()) {
            List<BookResDto> bookResDtos = books.stream()
                    .map(book -> new BookResDto(book))
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(bookResDtos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("책이 존재하지 않습니다.");
        }
    }
}
