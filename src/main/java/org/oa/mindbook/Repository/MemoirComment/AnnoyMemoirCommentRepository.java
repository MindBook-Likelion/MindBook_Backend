package org.oa.mindbook.Repository.MemoirComment;

import org.oa.mindbook.Domain.Entity.MemoirComment.AnnoyMemoirComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnoyMemoirCommentRepository extends JpaRepository<AnnoyMemoirComment, Long> {

    @Query(value = "select c from AnnoyMemoirComment c where c.annoyMemoir.annoyMemoirId = :id")
    List<AnnoyMemoirComment> findByAnnoyMemoirId(Long id);
}
