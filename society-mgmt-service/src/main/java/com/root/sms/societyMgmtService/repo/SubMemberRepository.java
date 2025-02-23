package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.SubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubMemberRepository extends JpaRepository<SubMember, Long> {
}
