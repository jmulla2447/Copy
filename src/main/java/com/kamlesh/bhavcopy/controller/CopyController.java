package com.kamlesh.bhavcopy.controller;

import com.kamlesh.bhavcopy.dto.CopyQueryRequest;
import com.kamlesh.bhavcopy.service.CopyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CopyController {

    private final CopyService bhavcopyService;

    public CopyController(CopyService bhavcopyService) {
        this.bhavcopyService = bhavcopyService;

        try {
            this.bhavcopyService.loadBhavcopy("bhavcopy_file.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/query")
    public ResponseEntity<String> query(@RequestBody CopyQueryRequest request) {
        String result = bhavcopyService.query(request.getSymbol(), request.getField());
        return ResponseEntity.ok(result);
    }
}
