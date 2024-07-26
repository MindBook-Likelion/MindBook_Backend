package org.oa.mindbook.Repository.Memoir;

import org.oa.mindbook.Domain.Entity.Memoir.AnxietyMemoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnxietyMemoirRepository extends JpaRepository<AnxietyMemoir, Long> {
    List<AnxietyMemoir> findByStatus(String status);
}
