package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;

import java.util.List;

public class CountQueryStrategy implements QueryStrategy {

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        String series = params[0];  // Assuming first parameter is the series
        return records.stream()
                .filter(record -> record.getField("SERIES").equalsIgnoreCase(series))
                .count();
    }
}
