package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericQuery implements QueryStrategy {

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        if (params.length < 2) {
            throw new IllegalArgumentException("Invalid number of parameters. Expected: <column> <value>");
        }

        String column = params[0];  // This is the column to search in, e.g., "SYMBOL", "MARKET", etc.
        String searchValue = params[1];  // The value to search for in the specified column

        for (CsvRecord row : records) {
            // Search dynamically based on the provided column
            String fieldValue = row.getField(column);

            if (fieldValue.equalsIgnoreCase(searchValue)) {
                return row.toCsvString();  // Return the entire row as a CSV string
            }
        }

        return "No record found for " + column + " = " + searchValue;
    }

}
