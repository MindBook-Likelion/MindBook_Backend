package org.oa.mindbook.Controller.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "책 API", description = "책 API입니다.")
public class BookController {

    private final BookService bookService;

    @Operation(method = "POST", summary = "책 추가")
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

    @Operation(method = "GET", summary = "책장 조회")
    @GetMapping("/myBook")
    List<BookResDto> getBookList(@RequestParam Long userId) {
        List<BookResDto> bookResDto = bookService.findAllByUserId(userId);

        return bookResDto;
    }

    @Operation(method = "GET", summary = "책장에 있는 책 검색")
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
