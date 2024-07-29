package org.oa.mindbook.Repository.Memoir;

import org.oa.mindbook.Domain.Entity.Memoir.PastMemoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastMemoirRepository extends JpaRepository<PastMemoir, Long> {
    List<PastMemoir> findByStatus(String status);
}
