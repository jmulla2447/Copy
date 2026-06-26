package com.kamlesh.bhavcopy.repository;

import com.kamlesh.bhavcopy.entity.JobQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobQueueRepository extends JpaRepository<JobQueue, Long> {
    JobQueue findByReqid(UUID reqid);
}
