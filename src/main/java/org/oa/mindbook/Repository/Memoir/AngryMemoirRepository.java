package org.oa.mindbook.Repository.Memoir;

import org.oa.mindbook.Domain.Entity.Memoir.AngryMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AngryMemoirRepository extends JpaRepository<AngryMemoir, Long> {
    List<AngryMemoir> findByStatus(String status);

    void deleteAllByUser(User user);
}
