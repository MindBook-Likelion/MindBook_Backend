package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.oa.mindbook.Dto.request.Memoir.CreateSadMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.SadMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.SadMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.SadMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.SadMemoirCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SadMemoirService {

    private final SadMemoirRepository sadMemoirRepository;
    private final SadMemoirCommentRepository sadMemoirCommentRepository;

    @Transactional
    public void saveSadMemoir(CreateSadMemoirRequestDto createSadMemoirRequestDto) {

        SadMemoir sadMemoir = sadMemoirRepository.save(createSadMemoirRequestDto.toEntity());
    }

    @Transactional
    public SadMemoirResponseDto getSadMemoir(Long sadMemoirId) {
        SadMemoir sadMemoir = sadMemoirRepository.findById(sadMemoirId).orElseThrow();
        List<SadMemoirCommentResponseDto> commentList = sadMemoirCommentRepository.findBySadMemoirId(sadMemoirId).stream().map(
                comment -> {
                    return SadMemoirCommentResponseDto.builder()
                            .sadMemoirCommentId(comment.getSadMemoirCommentId())
//                            .nickname(comment.getUser().getUserId())
                            .nickname("nickname")
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();
                }
        ).collect(Collectors.toList());

        return SadMemoirResponseDto.of(sadMemoir, commentList);
    }
}
