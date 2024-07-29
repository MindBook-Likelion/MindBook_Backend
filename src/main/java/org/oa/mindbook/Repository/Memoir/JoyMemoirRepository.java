package org.oa.mindbook.Repository.Memoir;

import org.oa.mindbook.Domain.Entity.Memoir.JoyMemoir;
import org.oa.mindbook.Dto.request.Memoir.CreateJoyMemoirRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JoyMemoirRepository extends JpaRepository<JoyMemoir, Long> {

    List<JoyMemoir> findByStatus(String status);
}
