package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.Memoir.CreateAnxietyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AnxietyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AnxietyMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.AnxietyMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.AnxietyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AnxietyMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnxietyMemoirService {

    private final AnxietyMemoirRepository anxietyMemoirRepository;
    private final AnxietyMemoirCommentRepository anxietyMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveAnxietyMemoir(CreateAnxietyMemoirRequestDto createAnxietyMemoirRequestDto) {
        User user = userRepository.findById(createAnxietyMemoirRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invaild user ID : " + createAnxietyMemoirRequestDto.getUserId()));

        AnxietyMemoir anxietyMemoir = AnxietyMemoir.builder()
                .user(user)
                .memory(createAnxietyMemoirRequestDto.getMemory())
                .impression(createAnxietyMemoirRequestDto.getImpression())
                .status(createAnxietyMemoirRequestDto.getStatus())
                .build();

        AnxietyMemoir newAnxietyMemoir = anxietyMemoirRepository.save(anxietyMemoir);
        return newAnxietyMemoir.getAnxietyMemoirId();


    }

    @Transactional
    public AnxietyMemoirResponseDto getAnxietyMemoir(Long anxietyMemoirId, Long userId) {
        AnxietyMemoir anxietyMemoir = anxietyMemoirRepository.findById(anxietyMemoirId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        List<AnxietyMemoirCommentResponseDto> commentList = anxietyMemoirCommentRepository.findByAnxietyMemoirId(anxietyMemoirId).stream().map(
                comment -> {
                    return AnxietyMemoirCommentResponseDto.builder()
                            .anxietyMemoirCommentId(comment.getAnxietyMemoirCommentId())
                            .nickname(comment.getUser().getNickName())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();

                }
        ).collect(Collectors.toList());

        return AnxietyMemoirResponseDto.of(anxietyMemoir, commentList, user);
    }

    @Transactional
    public List<AnxietyMemoirListResponseDto> getAnxietyMemoirList(String status, Long userId) {
        List<AnxietyMemoir> anxietyMemoirList = anxietyMemoirRepository.findByStatus(status);
        User user = userRepository.findById(userId).orElseThrow();

        List<AnxietyMemoirListResponseDto> responseDtoList = anxietyMemoirList.stream().map(
                anxietyMemoir -> {
                    return AnxietyMemoirListResponseDto.builder()
                            .anxietyMemoirId(anxietyMemoir.getAnxietyMemoirId())
                            .nickName(anxietyMemoir.getUser().getNickName())
                            .createdAt(anxietyMemoir.getCreatedAt())
                            .status(anxietyMemoir.getStatus())
                            .build();
                }
        ).collect(Collectors.toList());

        return responseDtoList;
    }
}
