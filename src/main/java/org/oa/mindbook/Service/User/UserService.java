package org.oa.mindbook.Service.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.User.CreateUserRequestDto;
import org.oa.mindbook.Dto.request.User.UpdateUserRequestDto;
import org.oa.mindbook.Dto.response.User.UserResponseDto;
import org.oa.mindbook.Repository.Book.BookReportRepository;
import org.oa.mindbook.Repository.Book.BookRepository;
import org.oa.mindbook.Repository.Memoir.*;
import org.oa.mindbook.Repository.MemoirComment.*;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JoyMemoirRepository joyMemoirRepository;
    private final AnxietyMemoirRepository anxietyMemoirRepository;
    private final AnnoyMemoirRepository annoyMemoirRepository;
    private final AngryMemoirRepository angryMemoirRepository;
    private final SadMemoirRepository sadMemoirRepository;
    private final PastMemoirRepository pastMemoirRepository;
    private final JoyMemoirCommentRepository joyMemoirCommentRepository;
    private final AnxietyMemoirCommentRepository anxietyMemoirCommentRepository;
    private final AnnoyMemoirCommentRepository annoyMemoirCommentRepository;
    private final AngryMemoirCommentRepository angryMemoirCommentRepository;
    private final SadMemoirCommentRepository sadMemoirCommentRepository;
    private final PastMemoirCommentRepository pastMemoirCommentRepository;
    private final BookRepository bookRepository;
    private final BookReportRepository bookReportRepository;

    @Transactional
    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        User user = createUserRequestDto.toEntity(passwordEncoder);
        Optional<User> findUser = userRepository.findByEmail(createUserRequestDto.getEmail());
        if (findUser.isPresent()) {
            throw new RuntimeException("이미 존재하는 유저입니다.");
        }
        userRepository.save(user);
        return UserResponseDto.from(user);
    }
    public UserResponseDto getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 존재히지 않습니다."));
        return UserResponseDto.from(user);
    }

    public Long findUserIdByEmail(String email) {
        // 이메일로 사용자 정보를 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        // 사용자 ID 반환
        return user.getId();
    }


    @Transactional
    public UserResponseDto updateUser(String email, UpdateUserRequestDto userRequestDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        if (userRequestDto.getPassword() != null && !userRequestDto.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
            userRequestDto.setPassword(encodedPassword);
        }

        user.update(userRequestDto);
        userRepository.save(user);
        return UserResponseDto.from(user);
    }

    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        joyMemoirCommentRepository.deleteAllByUser(user);
        anxietyMemoirCommentRepository.deleteAllByUser(user);
        annoyMemoirCommentRepository.deleteAllByUser(user);
        angryMemoirCommentRepository.deleteAllByUser(user);
        sadMemoirCommentRepository.deleteAllByUser(user);
        pastMemoirCommentRepository.deleteAllByUser(user);
        joyMemoirRepository.deleteAllByUser(user);
        anxietyMemoirRepository.deleteAllByUser(user);
        annoyMemoirRepository.deleteAllByUser(user);
        angryMemoirRepository.deleteAllByUser(user);
        sadMemoirRepository.deleteAllByUser(user);
        pastMemoirRepository.deleteAllByUser(user);
        bookReportRepository.deleteAllByUser(user);
        bookRepository.deleteAllByUser(user);


        userRepository.deleteByEmail(email);


    }

    public Map<String, String> getDaysSinceJoinedByEmail(String email) {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        // 닉네임을 가져옴
        String nickName = user.getNickName();

        // LocalDate로 변환
        String createdAtStr = user.getCreatedAt(); // 예를 들어 '2024.08.03'
        LocalDate createdAt = LocalDate.parse(createdAtStr, DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        // 현재 날짜와의 차이 계산
        long daysSinceJoined = Duration.between(createdAt.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();

        String message = String.format("%s님이 마음책방에 오신지 %d일 되었어요! 😀", nickName, daysSinceJoined);

        Map<String, String> response = new HashMap<>();
        response.put("message", message);

        return response;
    }

}