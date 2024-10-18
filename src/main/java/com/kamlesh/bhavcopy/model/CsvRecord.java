package com.kamlesh.bhavcopy.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class CsvRecord {
    private final Map<String, String> fields = new LinkedHashMap<>();

    public CsvRecord(String[] headers, String[] values) {
        for (int i = 0; i < headers.length; i++) {
            fields.put(headers[i], values[i]);
        }
    }

    public String getField(String fieldName) {
        return fields.getOrDefault(fieldName, "Field '" + fieldName + "' not found");
    }

    public String toCsvString() {
        return String.join(",", fields.values());
    }

    public Map<String, String> getFields() {
        return fields;
    }
}
