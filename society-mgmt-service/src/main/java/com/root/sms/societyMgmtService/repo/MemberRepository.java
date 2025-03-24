package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(nativeQuery = true, value = "SELECT m.mid, m.first_name, m.last_name, m.email, m.hashed_password, m.type, m.rid, m.created_at, m.updated_at FROM members m, societies s, rooms r \n" +
            "where s.sid = :sid and s.sid = r.sid and m.rid = r.rid and m.mid NOT IN (SELECT parking_space_allotment.mid from parking_space_allotment)")
    List<Member> getMembersWithNoParking(@Param("sid") Long societyId);

    @Query(nativeQuery = true, value = "SELECT m.mid, m.first_name, m.last_name, m.email, m.type, m.rid FROM members m, societies s, rooms r \n" +
            "where s.sid = :sid and s.sid = r.sid and m.rid = r.rid")
    List<Member> getMembersBySociety(@Param("sid") Long societyId);

}
