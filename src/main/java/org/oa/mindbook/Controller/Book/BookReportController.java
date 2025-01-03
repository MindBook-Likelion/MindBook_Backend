package org.oa.mindbook.Controller.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.oa.mindbook.Dto.request.Book.BookReportReqDto;
import org.oa.mindbook.Dto.response.Book.BookReportResDto;
import org.oa.mindbook.Service.Book.BookReportService;
import org.oa.mindbook.Service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Tag(name = "독후감 API", description = "독후감 API입니다.")
public class BookReportController {

    private final BookReportService bookReportService;

    private final UserService userService;

    @Operation(method = "POST", summary = "독후감 작성")
    @PostMapping("/bookReport")
    public ResponseEntity<?> saveReport(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestBody BookReportReqDto bookReportReqDto) {
        // 현재 로그인한 사용자의 이메일을 가져옵니다.
        String email = userDetails.getUsername();

        // 이메일을 통해 사용자 ID를 찾습니다.
        Long userId = userService.findUserIdByEmail(email);

        Long bookReportId = bookReportService.save(userId, bookReportReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("독후감 ID: " + bookReportId);
    }

    @Operation(method = "GET", summary = "독후감 상세 조회")
    @GetMapping("/bookReport")
    public ResponseEntity<?> getReport(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestParam Long bookId) {
        try {
            String email = userDetails.getUsername(); // Assuming userDetails contains user email
            BookReportResDto bookReportResDto = bookReportService.getReportByBookIdAndUserEmail(bookId, email);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", bookReportResDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @Operation(method = "DELETE", summary = "독후감 삭제")
    @DeleteMapping("/bookReport")
    public ResponseEntity<?> deleteReport(@AuthenticationPrincipal UserDetails userDetails,
                                          @RequestParam Long bookId) {
        try {
            String email = userDetails.getUsername(); // Assuming userDetails contains user email
            bookReportService.deleteReportByBookIdAndUserEmail(bookId, email);
            return ResponseEntity.status(HttpStatus.OK).body("{\"success\": true, \"message\": \"독후감 삭제가 완료되었습니다.\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }
}
