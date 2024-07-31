package org.oa.mindbook.Controller.Book;

import lombok.RequiredArgsConstructor;
import org.oa.mindbook.Dto.request.Book.BookReportReqDto;
import org.oa.mindbook.Dto.response.Book.BookReportResDto;
import org.oa.mindbook.Service.Book.BookReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class BookReportController {
    private final BookReportService bookReportService;

    @PostMapping("/bookReport")
    public ResponseEntity<?> saveReport(@RequestBody BookReportReqDto bookReportReqDto) {
        Long bookReportId = bookReportService.save(bookReportReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("독후감 ID: " + bookReportId);
    }

    @GetMapping("/bookReport")
    public ResponseEntity<?> getReport(@RequestParam Long reportId) {
        try {
            BookReportResDto bookReportResDto = bookReportService.getReportById(reportId);
            return ResponseEntity.status(HttpStatus.OK).body(bookReportResDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/bookReport")
    public ResponseEntity<?> deleteReport(@RequestParam Long reportId) {
        try {
            bookReportService.deleteReportById(reportId);
            return ResponseEntity.status(HttpStatus.OK).body("독후감 삭제가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
