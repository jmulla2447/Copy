package com.kamlesh.bhavcopy.repository;

import com.kamlesh.bhavcopy.entity.CsvRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<CsvRecordEntity, UUID> {
    @Procedure(name = "handle_query")
    List<String> handleQuery(@Param("query_type") String queryType,
                             @Param("param1") String param1,
                             @Param("param2") Double param2);
}
