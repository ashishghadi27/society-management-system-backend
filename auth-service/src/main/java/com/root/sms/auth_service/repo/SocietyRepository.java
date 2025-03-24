package com.root.sms.auth_service.repo;

import com.root.sms.auth_service.entity.Society;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocietyRepository extends JpaRepository<Society,Long> {
    Optional<Society> findBySidAndIsApprovedTrue(Long id);
}
