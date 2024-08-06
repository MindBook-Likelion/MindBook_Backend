package org.oa.mindbook.Repository.Memoir;

import org.oa.mindbook.Domain.Entity.Memoir.AnnoyMemoir;
import org.oa.mindbook.Domain.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnoyMemoirRepository extends JpaRepository<AnnoyMemoir, Long> {
    List<AnnoyMemoir> findByStatus(String status);

    void deleteAllByUser(User user);

    List<AnnoyMemoir> findByUserId(Long userId);
}
