package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.JoyMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateJoyMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.JoyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.JoyMemoirCommentRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoyMemoirCommentService {

    private final JoyMemoirCommentRepository joyMemoirCommentRepository;
    private final JoyMemoirRepository joyMemoirRepository;
    @Transactional
    public void saveJoyMemoirComment(CreateJoyMemoirCommentRequestDto dto) {
        JoyMemoir joyMemoir = joyMemoirRepository.findById(dto.getJoyMemoirId()).orElseThrow();

        joyMemoirCommentRepository.save(JoyMemoirComment.builder()
                .userId(dto.getUserId())
                .joyMemoir(joyMemoir)
                .content(dto.getContent())
                .build());
    }

    @Transactional
    public void deleteJoyMemoirComment(Long joyMemoirCommentId) {
        JoyMemoirComment joyMemoirComment = joyMemoirCommentRepository.findById(joyMemoirCommentId).orElseThrow();

        joyMemoirCommentRepository.deleteById(joyMemoirComment.getJoyMemoirCommentId());
    }
}
