package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.Memoir.CreateAnnoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AnnoyMemoirListResponseDto;
import org.oa.mindbook.Dto.response.Memoir.AnnoyMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.AnnoyMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.AnnoyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AnnoyMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnoyMemoirService {

    private final AnnoyMemoirRepository annoyMemoirRepository;
    private final AnnoyMemoirCommentRepository annoyMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveAnnoyMemoir(Long userId,CreateAnnoyMemoirRequestDto createAnnoyMemoirRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invaild user ID : " + userId));

        AnnoyMemoir annoyMemoir = AnnoyMemoir.builder()
                .user(user)
                .memory(createAnnoyMemoirRequestDto.getMemory())
                .impression(createAnnoyMemoirRequestDto.getImpression())
                .status(createAnnoyMemoirRequestDto.getStatus())
                .build();

        AnnoyMemoir newAnnoyMemoir = annoyMemoirRepository.save(annoyMemoir);
        return newAnnoyMemoir.getAnnoyMemoirId();

    }

    @Transactional
    public AnnoyMemoirResponseDto getAnnoyMemoir(Long annoyMemoirId, Long userId) {
        AnnoyMemoir annoyMemoir = annoyMemoirRepository.findById(annoyMemoirId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        List<AnnoyMemoirCommentResponseDto> commentList = annoyMemoirCommentRepository.findByAnnoyMemoirId(annoyMemoirId).stream().map(
                comment -> {
                    return AnnoyMemoirCommentResponseDto.builder()
                            .annoyMemoirCommentId(comment.getAnnoyMemoirCommentId())
                            .nickname(comment.getUser().getNickName())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();

                }
        ).collect(Collectors.toList());

        return AnnoyMemoirResponseDto.of(annoyMemoir, commentList, user);
    }

    @Transactional
    public List<AnnoyMemoirListResponseDto> getAnnoyMemoirList(String status, Long userId) {
        List<AnnoyMemoir> annoyMemoirList = annoyMemoirRepository.findByStatus(status);
        User user = userRepository.findById(userId).orElseThrow();

        List<AnnoyMemoirListResponseDto> responseDtoList = annoyMemoirList.stream().map(
                annoyMemoir -> {
                    return AnnoyMemoirListResponseDto.builder()
                            .annoyMemoirId(annoyMemoir.getAnnoyMemoirId())
                            .nickName(annoyMemoir.getUser().getNickName())
                            .createdAt(annoyMemoir.getCreatedAt())
                            .status(annoyMemoir.getStatus())
                            .build();
                }
        ).collect(Collectors.toList());

        return responseDtoList;


    }

    @Transactional
    public List<AnnoyMemoirListResponseDto> getMyAnnoyMemoirList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<AnnoyMemoir> annoyMemoirList = annoyMemoirRepository.findByUserId(userId);

        List<AnnoyMemoirListResponseDto> responseDtoList = annoyMemoirList.stream().map(
                annoyMemoir -> {
                    return AnnoyMemoirListResponseDto.builder()
                            .annoyMemoirId(annoyMemoir.getAnnoyMemoirId())
                            .nickName(annoyMemoir.getUser().getNickName())
                            .createdAt(annoyMemoir.getCreatedAt())
                            .status(annoyMemoir.getStatus())
                            .build();
                }
        ).collect(Collectors.toList());

        return responseDtoList;


    }
}
