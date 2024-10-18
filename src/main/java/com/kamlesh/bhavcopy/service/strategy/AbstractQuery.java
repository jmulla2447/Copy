package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;

import java.util.List;

public abstract class AbstractQuery implements QueryStrategy {

    public static final String DEFAULT_COL = "SYMBOL";

    protected String getDefaultColumn() {
        return DEFAULT_COL;
    }

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        if (params.length < 1) {
            throw new IllegalArgumentException("Invalid number of parameters. Expected: <column> <value>");
        }

        String value = params[0];
        String column = getValueOrDefault(params);

        return executeQuery(records, column, value);
    }

    private String getValueOrDefault(String[] params) {
        if (params.length > 1 && params[1] != null && !params[1].isEmpty()) {
            return params[1];
        }
        return getDefaultColumn();
    }


    // Abstract method to be implemented by subclasses for specific query logic
    protected abstract Object executeQuery(List<CsvRecord> records, String column, String value);
}
