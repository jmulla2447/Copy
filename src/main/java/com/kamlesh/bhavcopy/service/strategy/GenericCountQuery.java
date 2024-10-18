package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericCountQuery implements QueryStrategy {

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        if (params.length < 2) {
            throw new IllegalArgumentException("Invalid number of parameters. Expected: <column> <value>");
        }

        String column = params[0];  // The column to search in (e.g., "SERIES", "MARKET", etc.)
        String value = params[1];   // The value to search for in the specified column

        return records.parallelStream()
                .filter(row -> row.getField(column).equalsIgnoreCase(value))
                .count();
    }
}
