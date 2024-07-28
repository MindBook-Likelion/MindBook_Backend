package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.PastMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreatePastMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.PastMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.PastMemoirCommentRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PastMemoirCommentService {

    private final PastMemoirRepository pastMemoirRepository;
    private final PastMemoirCommentRepository pastMemoirCommentRepository;
    @Transactional
    public void savePastMemoirComment(CreatePastMemoirCommentRequestDto dto) {
        PastMemoir pastMemoir = pastMemoirRepository.findById(dto.getPastMemoirId()).orElseThrow();

        pastMemoirCommentRepository.save(PastMemoirComment.builder()
                .userId(dto.getUserId())
                .pastMemoir(pastMemoir)
                .content(dto.getContent())
                .build());
    }
}
