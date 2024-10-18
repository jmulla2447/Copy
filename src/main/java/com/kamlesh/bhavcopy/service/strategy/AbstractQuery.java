package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;

import java.util.List;

public abstract class AbstractQuery implements QueryStrategy {

    public static final String DEFAULT_COL = "SYMBOL";

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        if (params.length < 2) {
            throw new IllegalArgumentException("Invalid number of parameters. Expected: <column> <value>");
        }

        String column = params[0];
        String value = getValueOrDefault(params, 1);

        return executeQuery(records, column, value);
    }

    private String getValueOrDefault(String[] params, int index) {
        if (params.length > index && params[index] != null && !params[index].isEmpty()) {
            return params[index];
        }
        return DEFAULT_COL;
    }


    // Abstract method to be implemented by subclasses for specific query logic
    protected abstract Object executeQuery(List<CsvRecord> records, String column, String value);
}
