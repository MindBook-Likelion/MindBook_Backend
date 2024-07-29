package org.oa.mindbook.Repository.User;

import org.oa.mindbook.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
