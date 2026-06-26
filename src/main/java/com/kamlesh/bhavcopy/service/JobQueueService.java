package com.kamlesh.bhavcopy.service;


import com.kamlesh.bhavcopy.entity.JobQueue;
import com.kamlesh.bhavcopy.repository.JobQueueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class JobQueueService {

    private final JobQueueRepository jobQueueRepository;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    public JobQueueService(JobQueueRepository jobQueueRepository) {
        this.jobQueueRepository = jobQueueRepository;
    }

    @Transactional
    public UUID queueJob(String params) {
        UUID reqid = UUID.randomUUID();
        JobQueue jobQueue = new JobQueue();
        jobQueue.setReqid(reqid);
        jobQueue.setParams(params);
        jobQueue.setStatus("pending");

        // Save to the database
        jobQueueRepository.save(jobQueue);

        // Process job in a background thread
        executorService.submit(() -> processJob(jobQueue));

        return reqid;
    }

    private void processJob(JobQueue jobQueue) {
        try {
            // Simulate long-running computation
            int sleepTime = 10 + (int) (Math.random() * 80); // Random sleep between 10-90 seconds
            Thread.sleep(TimeUnit.SECONDS.toMillis(sleepTime));

            // Update job status
            jobQueue.setStartedat(LocalDateTime.now());
            jobQueue.setStatus("done");
            jobQueue.setEndedat(LocalDateTime.now());
            jobQueue.setDuration(Duration.between(jobQueue.getStartedat(), jobQueue.getEndedat()));

            // Add response handling here (for demonstration, just echo params)
            jobQueue.setResponse("{\"message\": \"Processed with params: " + jobQueue.getParams() + "\"}");

            // Save updates back to the database
            jobQueueRepository.save(jobQueue);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Handle interruption
        }
    }

    public String checkJobStatus(UUID reqid) {
        JobQueue jobQueue = jobQueueRepository.findByReqid(reqid);
        if (jobQueue == null) {
            return "Job not found";
        }
        if ("done".equals(jobQueue.getStatus())) {
            return jobQueue.getResponse();
        }
        return "trylater";
    }
}
