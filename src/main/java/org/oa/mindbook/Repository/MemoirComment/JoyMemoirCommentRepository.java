package org.oa.mindbook.Repository.MemoirComment;

import org.oa.mindbook.Domain.Entity.MemoirComment.JoyMemoirComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JoyMemoirCommentRepository extends JpaRepository<JoyMemoirComment, Long> {
    @Query(value = "select c from JoyMemoirComment c where c.joyMemoir.joyMemoirId = :id")
    List<JoyMemoirComment> findByJoyMemoirId(Long id);
}
