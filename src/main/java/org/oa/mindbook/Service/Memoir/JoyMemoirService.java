package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.Memoir.CreateJoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.JoyMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.JoyMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.JoyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.JoyMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoyMemoirService {

    private final JoyMemoirRepository joyMemoirRepository;
    private final JoyMemoirCommentRepository joyMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveJoyMemoir(Long userId, CreateJoyMemoirRequestDto createJoyMemoirRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invaild user ID : " + userId));

        JoyMemoir joyMemoir = JoyMemoir.builder()
                .user(user)
                .memory(createJoyMemoirRequestDto.getMemory())
                .status(createJoyMemoirRequestDto.getStatus())
                .build();

        JoyMemoir newJoyMemoir = joyMemoirRepository.save(joyMemoir);
        return newJoyMemoir.getJoyMemoirId();
    }

    @Transactional
    public JoyMemoirResponseDto getJoyMemoir(Long joyMemoirId, Long userId) {
        JoyMemoir joyMemoir = joyMemoirRepository.findById(joyMemoirId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        List<JoyMemoirCommentResponseDto> commentList = joyMemoirCommentRepository.findByJoyMemoirId(joyMemoirId).stream().map(
                comment -> {
                    return JoyMemoirCommentResponseDto.builder()
                            .joyMemoirCommentId(comment.getJoyMemoirCommentId())
                            .nickname(comment.getUser().getNickName())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();
                }
        ).collect(Collectors.toList());

        return JoyMemoirResponseDto.of(joyMemoir, commentList, user);
    }

    @Transactional
        public List<JoyMemoirListResponseDto> getJoyMemoirList(String status, Long userId) {
            List<JoyMemoir> joyMemoirList = joyMemoirRepository.findByStatus(status);
            User user = userRepository.findById(userId).orElseThrow();

            List<JoyMemoirListResponseDto> responseDtoList = joyMemoirList.stream().map(
                    joyMemoir -> {
                        return JoyMemoirListResponseDto.builder()
                                .joyMemoirId(joyMemoir.getJoyMemoirId())
                                .nickName(joyMemoir.getUser().getNickName())
                                .createdAt(joyMemoir.getCreatedAt())
                                .status(joyMemoir.getStatus())
                                .build();
                    }
            ).collect(Collectors.toList());

            return responseDtoList;
    }

    @Transactional
    public List<JoyMemoirListResponseDto> getMyJoyMemoirList(Long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        List<JoyMemoir> joyMemoirList = joyMemoirRepository.findByUserId(userId);

        List<JoyMemoirListResponseDto> responseDtoList = joyMemoirList.stream().map(
                joyMemoir -> {
                    return JoyMemoirListResponseDto.builder()
                            .joyMemoirId(joyMemoir.getJoyMemoirId())
                            .nickName(joyMemoir.getUser().getNickName())
                            .createdAt(joyMemoir.getCreatedAt())
                            .status(joyMemoir.getStatus())
                            .build();
                }
        ).collect(Collectors.toList());

        return responseDtoList;
    }
}
