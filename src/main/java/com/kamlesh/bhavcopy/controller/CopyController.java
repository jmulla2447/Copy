package com.kamlesh.bhavcopy.controller;

import com.kamlesh.bhavcopy.dto.CopyQueryRequest;
import com.kamlesh.bhavcopy.dto.FileLoadException;
import com.kamlesh.bhavcopy.service.CopyService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CopyController {

    public static final String FILENAME = "bhavcopy_file.csv";
    private final CopyService bhavcopyService;

    public CopyController(CopyService bhavcopyService) {
        this.bhavcopyService = bhavcopyService;

        try {
            this.bhavcopyService.loadCopy(FILENAME);
        } catch (IOException | CsvValidationException ex) {
            throw new FileLoadException("Error loading the file: " + FILENAME, ex);
        }
    }

    @PostMapping("/query")
    public ResponseEntity<String> query(@RequestBody CopyQueryRequest request) {
        String result = bhavcopyService.query(request.getSymbol(), request.getField());
        return ResponseEntity.ok(result);
    }
}
