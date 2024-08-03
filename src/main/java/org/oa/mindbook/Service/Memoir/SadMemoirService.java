package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.Memoir.CreateSadMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.SadMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.SadMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.SadMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.SadMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.SadMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SadMemoirService {

    private final SadMemoirRepository sadMemoirRepository;
    private final SadMemoirCommentRepository sadMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveSadMemoir(Long userId, CreateSadMemoirRequestDto createSadMemoirRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invaild user ID : " + userId));

        SadMemoir sadMemoir = SadMemoir.builder()
                .user(user)
                .memory(createSadMemoirRequestDto.getMemory())
                .impression(createSadMemoirRequestDto.getImpression())
                .status(createSadMemoirRequestDto.getStatus())
                .build();

        SadMemoir newSadMemoir = sadMemoirRepository.save(sadMemoir);
        return newSadMemoir.getSadMemoirId();

    }

    @Transactional
    public SadMemoirResponseDto getSadMemoir(Long sadMemoirId, Long userId) {
        SadMemoir sadMemoir = sadMemoirRepository.findById(sadMemoirId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        List<SadMemoirCommentResponseDto> commentList = sadMemoirCommentRepository.findBySadMemoirId(sadMemoirId).stream().map(
                comment -> {
                    return SadMemoirCommentResponseDto.builder()
                            .sadMemoirCommentId(comment.getSadMemoirCommentId())
                            .nickname(comment.getUser().getNickName())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();
                }
        ).collect(Collectors.toList());

        return SadMemoirResponseDto.of(user, sadMemoir, commentList);
    }

    @Transactional
    public List<SadMemoirListResponseDto> getSadMemoirList(String status, Long userId) {
        List<SadMemoir> sadMemoirList = sadMemoirRepository.findByStatus(status);
        User user = userRepository.findById(userId).orElseThrow();

        List<SadMemoirListResponseDto> responseDtoList = sadMemoirList.stream().map(
                sadMemoir -> {
                    return SadMemoirListResponseDto.builder()
                            .sadMemoirId(sadMemoir.getSadMemoirId())
                            .nickName(sadMemoir.getUser().getNickName())
                            .createdAt(sadMemoir.getCreatedAt())
                            .status(sadMemoir.getStatus())
                            .build();
                }
        ).collect(Collectors.toList());

        return responseDtoList;
    }
}
