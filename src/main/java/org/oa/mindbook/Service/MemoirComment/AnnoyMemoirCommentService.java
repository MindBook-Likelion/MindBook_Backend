package org.oa.mindbook.Service.MemoirComment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Domain.Entity.MemoirComment.AnnoyMemoirComment;
import org.oa.mindbook.Dto.request.MemoirComment.CreateAnnoyMemoirCommentRequestDto;
import org.oa.mindbook.Repository.Memoir.AnnoyMemoirRepository;
import org.oa.mindbook.Repository.MemoirComment.AnnoyMemoirCommentRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnoyMemoirCommentService {

    private final AnnoyMemoirRepository annoyMemoirRepository;
    private final AnnoyMemoirCommentRepository annoyMemoirCommentRepository;

    @Transactional
    public void saveAnnoyMemoirComment(CreateAnnoyMemoirCommentRequestDto dto) {
        AnnoyMemoir annoyMemoir = annoyMemoirRepository.findById(dto.getAnnoyMemoirId()).orElseThrow();

        annoyMemoirCommentRepository.save(AnnoyMemoirComment.builder()
                .userId(dto.getUserId())
                .annoyMemoir(annoyMemoir)
                .content(dto.getContent())
                .build());

    }
}
