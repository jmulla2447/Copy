package com.kamlesh.bhavcopy.repository;

import com.kamlesh.bhavcopy.entity.CsvRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecordRepository extends JpaRepository<CsvRecordEntity, UUID> {

}
