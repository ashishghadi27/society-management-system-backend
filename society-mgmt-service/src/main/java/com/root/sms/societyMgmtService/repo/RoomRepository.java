package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
