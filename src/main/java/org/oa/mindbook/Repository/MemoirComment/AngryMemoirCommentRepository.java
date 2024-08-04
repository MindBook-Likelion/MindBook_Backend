package org.oa.mindbook.Repository.MemoirComment;

import org.oa.mindbook.Domain.Entity.MemoirComment.AngryMemoirComment;
import org.oa.mindbook.Domain.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AngryMemoirCommentRepository  extends JpaRepository<AngryMemoirComment, Long> {
    @Query(value = "select c from AngryMemoirComment c where c.angryMemoir.angryMemoirId = :id")
    List<AngryMemoirComment> findByAngryMemoirId(Long id);

    void deleteAllByUser(User user);
}
