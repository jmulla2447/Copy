package com.kamlesh.bhavcopy.service.factory;

import com.kamlesh.bhavcopy.model.CsvRecord;
import com.kamlesh.bhavcopy.service.strategy.QueryStrategy;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryContext {

    @Value("${copy.filename}")
    private Resource filename;
    private QueryStrategy strategy;
    private final List<CsvRecord> records =  new ArrayList<>();

    public void setStrategy(QueryStrategy strategy) {
        this.strategy = strategy;
    }

    public void loadCsvFile() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(filename.getInputStream()))) {
            String[] headers = reader.readNext();

            String[] values;
            while ((values = reader.readNext()) != null) {
                CsvRecord row = new CsvRecord(headers, values);
                records.add(row);
            }
        }
    }

    // Execute the current strategy using the loaded records and query parameters
    public Object executeQuery(String[] params) {
        if (strategy == null) {
            throw new IllegalStateException("Query strategy has not been set.");
        }
        if (records.isEmpty()) {
            throw new IllegalStateException("No CSV records loaded into the context.");
        }
        return strategy.execute(records, params);
    }
}
