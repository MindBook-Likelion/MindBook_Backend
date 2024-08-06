package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.Memoir.CreatePastMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.PastMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.PastMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.PastMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.PastMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.PastMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PastMemoirService {

    private final PastMemoirRepository pastMemoirRepository;
    private final PastMemoirCommentRepository pastMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long savePastMemoir(Long userId, CreatePastMemoirRequestDto createPastMemoirRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invaild user ID : " + userId));

        PastMemoir pastMemoir = PastMemoir.builder()
                .user(user)
                .pastAt(createPastMemoirRequestDto.getPastAt())
                .memory(createPastMemoirRequestDto.getMemory())
                .status(createPastMemoirRequestDto.getStatus())
                .build();

        PastMemoir newPastMemoir = pastMemoirRepository.save(pastMemoir);
        return newPastMemoir.getPastMemoirId();

    }

    @Transactional
    public PastMemoirResponseDto getPastMemoir(Long pastMemoirId, Long userId) {
        PastMemoir pastMemoir = pastMemoirRepository.findById(pastMemoirId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        List<PastMemoirCommentResponseDto> commentList = pastMemoirCommentRepository.findByPastMemoirId(pastMemoirId).stream().map(
                comment -> {
                    return PastMemoirCommentResponseDto.builder()
                            .pastMemoirCommentId(comment.getPastMemoirCommentId())
                            .nickname(comment.getUser().getNickName())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();
                }
        ).collect(Collectors.toList());

        return  PastMemoirResponseDto.of(pastMemoir, commentList, user);
    }

    @Transactional
    public List<PastMemoirListResponseDto> getPastMemoirList(String status, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<PastMemoir> pastMemoirList = pastMemoirRepository.findByStatus(status);


        List<PastMemoirListResponseDto> responseDtoList = pastMemoirList.stream().map(
                pastMemoir -> {
                    return PastMemoirListResponseDto.builder()
                            .pastMemoirId(pastMemoir.getPastMemoirId())
                            .nickName(pastMemoir.getUser().getNickName())
                            .createdAt(pastMemoir.getCreatedAt())
                            .status(pastMemoir.getStatus())
                            .build();
                }
        ).collect(Collectors.toList());


        return responseDtoList;
    }

    @Transactional
    public List<PastMemoirListResponseDto> getMyPastMemoirList(Long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        List<PastMemoir> pastMemoirList = pastMemoirRepository.findByUserId(userId);


        List<PastMemoirListResponseDto> responseDtoList = pastMemoirList.stream().map(
                pastMemoir -> {
                    return PastMemoirListResponseDto.builder()
                            .pastMemoirId(pastMemoir.getPastMemoirId())
                            .nickName(pastMemoir.getUser().getNickName())
                            .createdAt(pastMemoir.getCreatedAt())
                            .status(pastMemoir.getStatus())
                            .build();
                }
        ).collect(Collectors.toList());


        return responseDtoList;
    }
}
