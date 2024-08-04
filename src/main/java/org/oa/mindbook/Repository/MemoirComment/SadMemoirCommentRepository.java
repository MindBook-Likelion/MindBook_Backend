package org.oa.mindbook.Repository.MemoirComment;

import org.oa.mindbook.Domain.Entity.MemoirComment.SadMemoirComment;
import org.oa.mindbook.Domain.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SadMemoirCommentRepository extends JpaRepository<SadMemoirComment, Long> {
    @Query(value = "select c from SadMemoirComment c where c.sadMemoir.sadMemoirId = :id")
    List<SadMemoirComment> findBySadMemoirId(Long id);

    void deleteAllByUser(User user);
}
