package com.root.sms.societyMgmtService.repo;

import com.root.sms.societyMgmtService.entity.SocietyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyFilesRepository extends JpaRepository<SocietyFile, Long> {
}
