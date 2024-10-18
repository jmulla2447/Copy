package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericQuery extends AbstractQuery {
    @Override
    protected Object executeQuery(List<CsvRecord> records, String column, String value) {
        // Find the record where the specified column matches the value
        for (CsvRecord row : records) {
            if (row.getField(column).equalsIgnoreCase(value)) {
                return row.toCsvString();
            }
        }
        return "Record not found";
    }
}
