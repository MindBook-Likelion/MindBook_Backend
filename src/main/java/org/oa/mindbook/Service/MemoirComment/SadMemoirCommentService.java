package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.SadMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateSadMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.SadMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.SadMemoirCommentRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SadMemoirCommentService {

    private final SadMemoirRepository sadMemoirRepository;
    private final SadMemoirCommentRepository sadMemoirCommentRepository;

    @Transactional
    public void saveSadMemoirComment(CreateSadMemoirCommentRequestDto dto) {
        SadMemoir sadMemoir = sadMemoirRepository.findById(dto.getSadMemoirId()).orElseThrow();

        sadMemoirCommentRepository.save(SadMemoirComment.builder()
                .userId(dto.getUserId())
                .sadMemoir(sadMemoir)
                .content(dto.getContent())
                .build());
    }
}
