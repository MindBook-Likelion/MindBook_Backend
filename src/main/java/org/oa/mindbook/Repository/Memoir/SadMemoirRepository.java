package org.oa.mindbook.Repository.Memoir;

import org.oa.mindbook.Domain.Entity.Memoir.SadMemoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SadMemoirRepository extends JpaRepository<SadMemoir, Long> {
    List<SadMemoir> findByStatus(String status);

}
