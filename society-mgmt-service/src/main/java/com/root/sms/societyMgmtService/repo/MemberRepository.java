package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
