package com.kamlesh.bhavcopy.controller;

import com.kamlesh.bhavcopy.dto.CopyQueryRequest;
import com.kamlesh.bhavcopy.service.CopyService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CopyController {

    private final CopyService bhavcopyService;

    public CopyController(CopyService bhavcopyService) {
        this.bhavcopyService = bhavcopyService;
        try {
            this.bhavcopyService.loadCopy();
        } catch (IOException | CsvValidationException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @PostMapping("/query")
    public ResponseEntity<String> query(@RequestBody CopyQueryRequest request) {
        String queryType = request.getSymbol().toUpperCase();
        String[] params = new String[]{request.getField()};

        Object result = bhavcopyService.handleQuery(queryType, params);
        return ResponseEntity.ok(result.toString());
    }
}
