package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnnoyMemoirComment;
import org.oa.mindbook.Domain.Entity.User.User;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnnoyMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.AnnoyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AnnoyMemoirCommentRepository;
import org.oa.mindbook.Repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnoyMemoirCommentService {

    private final AnnoyMemoirRepository annoyMemoirRepository;
    private final AnnoyMemoirCommentRepository annoyMemoirCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveAnnoyMemoirComment(CreateAnnoyMemoirCommentRequestDto dto, Long userId) {
        AnnoyMemoir annoyMemoir = annoyMemoirRepository.findById(dto.getAnnoyMemoirId()).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

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

    @Transactional
    public List<AnnoyMemoirComment> getCommentsByAnnoyMemoirId(Long annoyMemoirId) {
        return annoyMemoirCommentRepository.findByAnnoyMemoirId(annoyMemoirId);
    }
}
