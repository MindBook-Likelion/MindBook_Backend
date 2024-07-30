package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.AngryMemoirComment;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnnoyMemoirComment;
import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAngryMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.AngryMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AngryMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AngryMemoirCommentService {

    private final AngryMemoirRepository angryMemoirRepository;
    private final AngryMemoirCommentRepository angryMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveAngryMemoirComment(CreateAngryMemoirCommentRequestDto dto) {
        AngryMemoir angryMemoir = angryMemoirRepository.findById(dto.getAngryMemoirId()).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        angryMemoirCommentRepository.save(AngryMemoirComment.builder()
                .user(user)
                .angryMemoir(angryMemoir)
                .content(dto.getContent())
                .build());
    }

    @Transactional
    public void deleteAngryMemoirComment(Long angryMemoirCommentId) {
       AngryMemoirComment angryMemoirComment = angryMemoirCommentRepository.findById(angryMemoirCommentId).orElseThrow();

        angryMemoirCommentRepository.deleteById(angryMemoirComment.getAngryMemoirCommentId());
    }
}
