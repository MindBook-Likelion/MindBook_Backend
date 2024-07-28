package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Dto.request.Memoir.CreateAnnoyMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.AnnoyMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.AnnoyMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.AnnoyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AnnoyMemoirCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnoyMemoirService {

    private final AnnoyMemoirRepository annoyMemoirRepository;
    private final AnnoyMemoirCommentRepository annoyMemoirCommentRepository;

    @Transactional
    public void saveAnnoyMemoir(CreateAnnoyMemoirRequestDto createAnnoyMemoirRequestDto) {

        AnnoyMemoir annoyMemoir = annoyMemoirRepository.save(createAnnoyMemoirRequestDto.toEntity());

    }

    @Transactional
    public AnnoyMemoirResponseDto getAnnoyMemoir(Long annoyMemoirId) {
        AnnoyMemoir annoyMemoir = annoyMemoirRepository.findById(annoyMemoirId).orElseThrow();
        List<AnnoyMemoirCommentResponseDto> commentList = annoyMemoirCommentRepository.findByAnnoyMemoirId(annoyMemoirId).stream().map(
                comment -> {
                    return AnnoyMemoirCommentResponseDto.builder()
                            .annoyMemoirCommentId(comment.getAnnoyMemoirCommentId())
//                            .nickname(comment.getUser().getUserId())
                            .nickname("nickname")
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();

                }
        ).collect(Collectors.toList());

        return AnnoyMemoirResponseDto.of(annoyMemoir, commentList);
    }
}
