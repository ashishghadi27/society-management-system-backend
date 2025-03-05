package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findBySocietyId(Long societyId);
}
