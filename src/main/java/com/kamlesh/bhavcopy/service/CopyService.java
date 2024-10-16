package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.model.CsvRecord;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CopyService {
    private final Map<String, CsvRecord> symbolData = new HashMap<>();

    public void loadBhavcopy(String filename) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] headers = reader.readNext();

            String[] values;
            while ((values = reader.readNext()) != null) {
                String symbol = values[0];
                CsvRecord record = new CsvRecord(headers, values);
                symbolData.put(symbol, record);
            }
        }
    }

    public String query(String symbol, String field) {
        CsvRecord record = symbolData.get(symbol);
        if (record == null) {
            return "Symbol '" + symbol + "' not found";   // Convert into RecordNotFoundException and handle into Globallz
        }
        return record.getField(field);
    }
}
