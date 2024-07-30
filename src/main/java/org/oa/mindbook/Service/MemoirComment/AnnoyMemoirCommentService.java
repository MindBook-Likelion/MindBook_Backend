package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnnoyMemoirComment;
import org.oa.mindbook.Domain.Entity.User;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnnoyMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.AnnoyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AnnoyMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnoyMemoirCommentService {

    private final AnnoyMemoirRepository annoyMemoirRepository;
    private final AnnoyMemoirCommentRepository annoyMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveAnnoyMemoirComment(CreateAnnoyMemoirCommentRequestDto dto) {
        AnnoyMemoir annoyMemoir = annoyMemoirRepository.findById(dto.getAnnoyMemoirId()).orElseThrow();
        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        annoyMemoirCommentRepository.save(AnnoyMemoirComment.builder()
                .user(user)
                .annoyMemoir(annoyMemoir)
                .content(dto.getContent())
                .build());

    }

    @Transactional
    public void deleteAnnoyMemoirComment(Long annoyMemoirCommentId) {
        AnnoyMemoirComment annoyMemoirComment = annoyMemoirCommentRepository.findById(annoyMemoirCommentId).orElseThrow();

        annoyMemoirCommentRepository.deleteById(annoyMemoirComment.getAnnoyMemoirCommentId());
    }
}
