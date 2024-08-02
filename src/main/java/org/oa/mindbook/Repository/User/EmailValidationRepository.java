package org.oa.mindbook.Repository.User;

import org.oa.mindbook.Domain.Entity.User.EmailValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailValidationRepository extends JpaRepository<EmailValidation, String> {
    Optional<EmailValidation> findByEmail(String email);

    List<EmailValidation> findAllByEmail(String email);
}