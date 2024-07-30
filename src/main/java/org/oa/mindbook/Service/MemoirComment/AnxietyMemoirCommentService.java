package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnxietyMemoirComment;
import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnxietyMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.AnxietyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AnxietyMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnxietyMemoirCommentService {

    private final AnxietyMemoirRepository anxietyMemoirRepository;
    private final AnxietyMemoirCommentRepository anxietyMemoirCommentRepository;
    private final UserRepository userRepository;
    @Transactional
    public void saveAnxietyMemoirComment(CreateAnxietyMemoirCommentRequestDto dto) {
        AnxietyMemoir anxietyMemoir = anxietyMemoirRepository.findById(dto.getAnxietyMemoirId()).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        anxietyMemoirCommentRepository.save(AnxietyMemoirComment.builder()
                .user(user)
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
