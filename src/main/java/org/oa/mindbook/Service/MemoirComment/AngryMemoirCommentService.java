package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.AngryMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAngryMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.AngryMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AngryMemoirCommentRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AngryMemoirCommentService {

    private final AngryMemoirRepository angryMemoirRepository;
    private final AngryMemoirCommentRepository angryMemoirCommentRepository;

    @Transactional
    public void saveAngryMemoirComment(CreateAngryMemoirCommentRequestDto dto) {
        AngryMemoir angryMemoir = angryMemoirRepository.findById(dto.getAngryMemoirId()).orElseThrow();

        angryMemoirCommentRepository.save(AngryMemoirComment.builder()
                .userId(dto.getUserId())
                .angryMemoir(angryMemoir)
                .content(dto.getContent())
                .build());
    }
}
