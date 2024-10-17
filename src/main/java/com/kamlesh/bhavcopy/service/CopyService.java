package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.model.CsvRecord;
import com.kamlesh.bhavcopy.service.factory.QueryFactory;
import com.kamlesh.bhavcopy.service.strategy.QueryStrategy;
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
    private final QueryContext context;  // Context for strategies
    private final QueryFactory queryFactory;  // Factory to create strategies
    @Value("${copy.filename}")
    private Resource filename;

    // Constructor injection of QueryFactory and QueryContext
    public CopyService(QueryFactory queryFactory, QueryContext queryContext) {
        this.queryFactory = queryFactory;
        this.context = queryContext;
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
        QueryStrategy strategy = queryFactory.getStrategy(queryType);

        // Set the strategy in the context
        context.setStrategy(strategy);

        // Execute the strategy
        return context.executeStrategy(records, params);
    }
}
