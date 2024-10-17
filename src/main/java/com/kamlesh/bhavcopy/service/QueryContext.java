package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.model.CsvRecord;
import com.kamlesh.bhavcopy.service.strategy.QueryStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryContext {

    private QueryStrategy strategy;

    // Setter to change the strategy dynamically
    public void setStrategy(QueryStrategy strategy) {
        this.strategy = strategy;
    }

    // Execute the current strategy with the given records and parameters
    public Object executeStrategy(List<CsvRecord> records, String[] params) {
        return strategy.execute(records, params);
    }
}
