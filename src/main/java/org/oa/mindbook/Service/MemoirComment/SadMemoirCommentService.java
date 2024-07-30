package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.SadMemoirComment;
import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.request.MemoirComment.CreateSadMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.SadMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.SadMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SadMemoirCommentService {

    private final SadMemoirRepository sadMemoirRepository;
    private final SadMemoirCommentRepository sadMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveSadMemoirComment(CreateSadMemoirCommentRequestDto dto) {
        SadMemoir sadMemoir = sadMemoirRepository.findById(dto.getSadMemoirId()).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        sadMemoirCommentRepository.save(SadMemoirComment.builder()
                .user(user)
                .sadMemoir(sadMemoir)
                .content(dto.getContent())
                .build());
    }

    @Transactional
    public void deleteSadMemoirComment(Long sadMemoirCommentId) {
        SadMemoirComment sadMemoirComment = sadMemoirCommentRepository.findById(sadMemoirCommentId).orElseThrow();

        sadMemoirCommentRepository.deleteById(sadMemoirComment.getSadMemoirCommentId());
    }
}
