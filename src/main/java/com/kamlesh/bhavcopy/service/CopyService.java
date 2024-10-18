package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.model.CsvRecord;
import com.kamlesh.bhavcopy.service.factory.QueryContext;
import com.kamlesh.bhavcopy.service.factory.QueryFactory;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CopyService {

    private final List<CsvRecord> records = new ArrayList<>();  // Loaded records
    private final QueryFactory queryFactory;  // Factory to create strategies
    @Value("${copy.filename}")
    private Resource filename;

    // Constructor injection of QueryFactory and QueryContext
    public CopyService(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public void loadCopy() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(filename.getInputStream()))) {
            String[] headers = reader.readNext();

            String[] values;
            while ((values = reader.readNext()) != null) {
                CsvRecord record = new CsvRecord(headers, values);
                records.add(record);
            }
        }
    }

    public Object handleQuery(String queryType, String[] params) {
        // Get the strategy from the factory
        QueryContext context = queryFactory.getContext(queryType);

        // Execute the strategy
        return context.executeQuery(records, params);
    }
}
