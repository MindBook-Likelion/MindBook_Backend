package org.oa.mindbook.Controller.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "독후감 API", description = "독후감 API입니다.")
public class BookReportController {
    private final BookReportService bookReportService;

    @Operation(method = "POST", summary = "독후감 작성")
    @PostMapping("/bookReport")
    public ResponseEntity<?> saveReport(@RequestBody BookReportReqDto bookReportReqDto) {
        Long bookReportId = bookReportService.save(bookReportReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("독후감 ID: " + bookReportId);
    }

    @Operation(method = "GET", summary = "독후감 상세 조회")
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

    @Operation(method = "DELETE", summary = "독후감 삭제")
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
