package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.model.CsvRecord;
import com.kamlesh.bhavcopy.service.factory.QueryFactory;
import com.kamlesh.bhavcopy.service.strategy.QueryStrategy;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CopyService {
    private final List<CsvRecord> records = new ArrayList<>();
    private QueryContext context ;
    private QueryFactory queryFactory;

    public CopyService(QueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    public void loadCopy(String filename) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] headers = reader.readNext();

            String[] values;
            while ((values = reader.readNext()) != null) {
                String symbol = values[0];
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
