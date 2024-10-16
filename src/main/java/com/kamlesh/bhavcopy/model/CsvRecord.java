package com.kamlesh.bhavcopy.model;

import java.util.HashMap;
import java.util.Map;

public class CsvRecord {
    private Map<String, String> fields = new HashMap<>();

    public CsvRecord(String[] headers, String[] values) {
        for (int i = 0; i < headers.length; i++) {
            fields.put(headers[i], values[i]);
        }
    }

    public String getField(String fieldName) {
        return fields.getOrDefault(fieldName, "Field '" + fieldName + "' not found");
    }
}
