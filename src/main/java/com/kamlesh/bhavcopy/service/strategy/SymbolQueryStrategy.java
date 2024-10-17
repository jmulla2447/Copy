package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;

import java.util.List;

public class SymbolQueryStrategy implements QueryStrategy {

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        String symbol = params[0];
        for (CsvRecord record : records) {
            if (record.getField("SYMBOL").equalsIgnoreCase(symbol)) {
                return record.toCsvString();
            }
        }
        return "Symbol not found";
    }

}
