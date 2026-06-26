package com.kamlesh.bhavcopy.controller;

import com.kamlesh.bhavcopy.service.JobQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/job")
public class JobQueueController {

    private final JobQueueService jobQueueService;

    @Autowired
    public JobQueueController(JobQueueService jobQueueService) {
        this.jobQueueService = jobQueueService;
    }

    @PostMapping("/submit")
    public UUID submitJob(@RequestBody String params) {
        return jobQueueService.queueJob(params);
    }

    @GetMapping("/status/{reqid}")
    public String getJobStatus(@PathVariable UUID reqid) {
        return jobQueueService.checkJobStatus(reqid);
    }
}
