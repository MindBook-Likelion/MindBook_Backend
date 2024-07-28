package org.oa.mindbook.Service.Memoir;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.oa.mindbook.Dto.request.Memoir.CreatePastMemoirRequestDto;
import org.oa.mindbook.Dto.response.Memoir.PastMemoirResponseDto;
import org.oa.mindbook.Dto.response.MemoirComment.PastMemoirCommentResponseDto;
import org.oa.mindbook.Repository.Memoir.PastMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.PastMemoirCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PastMemoirService {

    private final PastMemoirRepository pastMemoirRepository;
    private final PastMemoirCommentRepository pastMemoirCommentRepository;

    @Transactional
    public void savePastMemoir(CreatePastMemoirRequestDto createPastMemoirRequestDto) {

        PastMemoir pastMemoir = pastMemoirRepository.save(createPastMemoirRequestDto.toEntity());

    }

    @Transactional
    public PastMemoirResponseDto getPastMemoir(Long pastMemoirId) {
        PastMemoir pastMemoir = pastMemoirRepository.findById(pastMemoirId).orElseThrow();
        List<PastMemoirCommentResponseDto> commentList = pastMemoirCommentRepository.findByPastMemoirId(pastMemoirId).stream().map(
                comment -> {
                    return PastMemoirCommentResponseDto.builder()
                            .pastMemoirCommentId(comment.getPastMemoirCommentId())
//                            .nickname(comment.getUser().getUerId())
                            .nickname("nickname")
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .build();
                }
        ).collect(Collectors.toList());

        return  PastMemoirResponseDto.of(pastMemoir, commentList);
    }
}
