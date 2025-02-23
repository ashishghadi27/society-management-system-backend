package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.Society;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyRepository extends JpaRepository<Society,Long> {
    List<Society> findByIsApprovedTrue();
    List<Society> findByNameContainingIgnoreCaseAndIsApprovedTrue(String name);
}
