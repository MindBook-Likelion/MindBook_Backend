package org.oa.mindbook.Controller.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.oa.mindbook.Domain.Entity.Book.Book;
import org.oa.mindbook.Dto.request.Book.BookDto;
import org.oa.mindbook.Dto.request.Book.BookReqDto;
import org.oa.mindbook.Dto.response.Book.BookResDto;
import org.oa.mindbook.Service.Book.BookService;
import org.oa.mindbook.Service.User.UserService;
import org.oa.mindbook.auth.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*" ,value = "*")
@Tag(name = "책 API", description = "책 API입니다.")
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    @Operation(method = "GET", summary = "책 검색 open api 사용")
    @GetMapping("/searchBook")
    public ResponseEntity<?> bookSearch(@RequestParam("keyword") String keyword,
                                        @RequestHeader("X-Naver-Client-Id") String CLIENT_ID,
                                        @RequestHeader("X-Naver-Client-Secret") String CLIENT_SECRET) {
        try {
            String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + encodedKeyword + "&display=100&sort=sim";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
            con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            List<BookDto> bookDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String title = obj.getString("title");
                String author = obj.getString("author");
                String publisher = obj.getString("publisher");
                String pubdate = obj.getString("pubdate");
                String description = obj.getString("description");
                String image = obj.getString("image");

                bookDtoList.add(BookDto.builder()
                        .title(title)
                        .author(author)
                        .publisher(publisher)
                        .pubdate(pubdate)
                        .description(description)
                        .image(image)
                        .build());
            }

            return ResponseEntity.status(HttpStatus.OK).body(bookDtoList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(method = "POST", summary = "책 추가")
    @PostMapping("/addBook")
    public ResponseEntity<?> saveBook(@RequestBody BookReqDto bookReqDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        try {
            // 현재 로그인한 사용자의 이메일을 가져옵니다.
            String email = customUserDetails.getUsername();

            // 이메일을 통해 사용자 ID를 찾습니다.
            Long userId = userService.findUserIdByEmail(email);


            // 책이 이미 존재하는지 확인
            if (bookService.existsByUserIdAndTitle(userId, bookReqDto.getTitle())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 추가된 책입니다.");
            }

            // 책을 저장
            Long bookId = bookService.save(bookReqDto, userId); // userId를 서비스에 전달
            return ResponseEntity.status(HttpStatus.CREATED).body(bookId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(method = "GET", summary = "책장 조회")
    @GetMapping("/myBook")
    public ResponseEntity<List<BookResDto>> getBookList(@AuthenticationPrincipal UserDetails userDetails) {
        // 현재 로그인한 사용자의 이메일을 가져옵니다.
        String email = userDetails.getUsername();

        // 이메일을 통해 사용자 ID를 찾습니다.
        Long userId = userService.findUserIdByEmail(email);

        List<BookResDto> bookResDto = bookService.findAllByUserId(userId);

        return ResponseEntity.ok(bookResDto);
    }

    @Operation(method = "GET", summary = "책장에 있는 책 검색")
    @GetMapping("/myBook/search")
    public ResponseEntity<?> searchBook(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String title) {
        // 현재 로그인한 사용자의 이메일을 가져옵니다.
        String email = userDetails.getUsername();

        // 이메일을 통해 사용자 ID를 찾습니다.
        Long userId = userService.findUserIdByEmail(email);

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
