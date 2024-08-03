package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.PastMemoirComment;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.MemoirComment.CreatePastMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.PastMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.PastMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PastMemoirCommentService {

    private final PastMemoirRepository pastMemoirRepository;
    private final PastMemoirCommentRepository pastMemoirCommentRepository;
    private final UserRepository userRepository;
    @Transactional
    public void savePastMemoirComment(CreatePastMemoirCommentRequestDto dto, Long userId) {
        PastMemoir pastMemoir = pastMemoirRepository.findById(dto.getPastMemoirId()).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        pastMemoirCommentRepository.save(PastMemoirComment.builder()
                .user(user)
                .pastMemoir(pastMemoir)
                .content(dto.getContent())
                .build());
    }

    @Transactional
    public void deletePastMemoirComment(Long pastMemoirCommentId) {
        PastMemoirComment pastMemoirComment = pastMemoirCommentRepository.findById(pastMemoirCommentId).orElseThrow();

        pastMemoirCommentRepository.deleteById(pastMemoirComment.getPastMemoirCommentId());
    }

    @Transactional
    public List<PastMemoirComment> getCommentsByPastMemoirId(Long pastMemoirId) {
        return pastMemoirCommentRepository.findByPastMemoirId(pastMemoirId);
    }
}
