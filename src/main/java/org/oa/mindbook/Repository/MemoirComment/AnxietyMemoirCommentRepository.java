package org.oa.mindbook.Repository.MemoirComment;

import org.oa.mindbook.Domain.Entity.MemoirComment.AnxietyMemoirComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnxietyMemoirCommentRepository extends JpaRepository<AnxietyMemoirComment, Long> {

    @Query(value = "select c from AnxietyMemoirComment c where c.anxietyMemoir.anxietyMemoirId = :id")
    List<AnxietyMemoirComment> findByAnxietyMemoirId(Long id);

}
