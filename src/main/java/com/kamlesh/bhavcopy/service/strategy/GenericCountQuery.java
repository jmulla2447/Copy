package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenericCountQuery extends AbstractQuery {

    @Override
    protected Object executeQuery(List<CsvRecord> records, String column, String value) {
        // Count the records where the specified column matches the value
        return records.parallelStream()
                .filter(row -> row.getField(column).equalsIgnoreCase(value))
                .count();
    }
}
