package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnxietyMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnxietyMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.AnxietyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AnxietyMemoirCommentRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnxietyMemoirCommentService {

    private final AnxietyMemoirRepository anxietyMemoirRepository;
    private final AnxietyMemoirCommentRepository anxietyMemoirCommentRepository;
    @Transactional
    public void saveAnxietyMemoirComment(CreateAnxietyMemoirCommentRequestDto dto) {
        AnxietyMemoir anxietyMemoir = anxietyMemoirRepository.findById(dto.getAnxietyMemoirId()).orElseThrow();

        anxietyMemoirCommentRepository.save(AnxietyMemoirComment.builder()
                .userId(dto.getUserId())
                .anxietyMemoir(anxietyMemoir)
                .content(dto.getContent())
                .build());



    }

    @Transactional
    public void deleteAnxietyMemoirComment(Long anxietyMemoirCommentId) {
        AnxietyMemoirComment anxietyMemoirComment = anxietyMemoirCommentRepository.findById(anxietyMemoirCommentId).orElseThrow();

        anxietyMemoirCommentRepository.deleteById(anxietyMemoirComment.getAnxietyMemoirCommentId());
    }
}
