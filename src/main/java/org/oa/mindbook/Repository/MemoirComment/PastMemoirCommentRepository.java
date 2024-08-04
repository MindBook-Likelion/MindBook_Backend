package org.oa.mindbook.Repository.MemoirComment;

import org.oa.mindbook.Domain.Entity.MemoirComment.PastMemoirComment;
import org.oa.mindbook.Domain.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PastMemoirCommentRepository extends JpaRepository<PastMemoirComment, Long> {
    @Query(value = "select c from PastMemoirComment c where c.pastMemoir.pastMemoirId = :id")
    List<PastMemoirComment> findByPastMemoirId(Long id);

    void deleteAllByUser(User user);
}
