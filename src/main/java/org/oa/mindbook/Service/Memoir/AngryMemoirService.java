package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.Memoir.CreateAngryMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AngryMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AngryMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.AngryMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.AngryMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AngryMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AngryMemoirService {

    private final AngryMemoirRepository angryMemoirRepository;
    private final AngryMemoirCommentRepository angryMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveAngryMemoir(Long userId ,CreateAngryMemoirRequestDto createAngryMemoirRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invaild user ID : " + userId));

        AngryMemoir angryMemoir = AngryMemoir.builder()
                .user(user)
                .memory(createAngryMemoirRequestDto.getMemory())
                .impression(createAngryMemoirRequestDto.getImpression())
                .status(createAngryMemoirRequestDto.getStatus())
                .build();

        AngryMemoir newAngryMemoir = angryMemoirRepository.save(angryMemoir);
        return newAngryMemoir.getAngryMemoirId();
    }

    @Transactional
    public AngryMemoirResponseDto getAngryMemoir(Long angryMemoirId, Long userId) {
        AngryMemoir angryMemoir = angryMemoirRepository.findById(angryMemoirId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        List<AngryMemoirCommentResponseDto> commentList = angryMemoirCommentRepository.findByAngryMemoirId(angryMemoirId).stream().map(
                comment -> {
                    return AngryMemoirCommentResponseDto.builder()
                            .angryMemoirCommentId(comment.getAngryMemoirCommentId())
                            .nickname(comment.getUser().getNickName())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();
                }
        ).collect(Collectors.toList());

        return AngryMemoirResponseDto.of(angryMemoir, commentList, user);
    }

    @Transactional
    public List<AngryMemoirListResponseDto> getAngryMemoirList(String status, Long userId) {
        List<AngryMemoir> angryMemoirList = angryMemoirRepository.findByStatus(status);
        User user = userRepository.findById(userId).orElseThrow();

        List<AngryMemoirListResponseDto> responseDtoList = angryMemoirList.stream().map(
                angryMemoir -> {
                    return AngryMemoirListResponseDto.builder()
                            .angryMemoirId(angryMemoir.getAngryMemoirId())
                            .nickName(angryMemoir.getUser().getNickName())
                            .createdAt(angryMemoir.getCreatedAt())
                            .status(angryMemoir.getStatus())
                            .build();
                }
        ).collect(Collectors.toList());

        return responseDtoList;
    }
}
