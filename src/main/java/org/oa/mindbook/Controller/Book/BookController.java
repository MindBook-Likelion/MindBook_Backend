package org.oa.mindbook.Controller.Book;

import lombok.RequiredArgsConstructor;
import org.oa.mindbook.Dto.request.Book.BookReqDto;
import org.oa.mindbook.Dto.response.Book.BookResDto;
import org.oa.mindbook.Service.Book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/addBook")
    ResponseEntity<?> saveBook(@RequestBody BookReqDto bookReqDto) {
        try {
            Long bookId = bookService.save(bookReqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1L);
        }
    }
}
