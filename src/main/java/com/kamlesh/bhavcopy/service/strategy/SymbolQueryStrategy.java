package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SymbolQueryStrategy implements QueryStrategy {

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        String symbol = params[0];
        for (CsvRecord row : records) {
            if (row.getField("SYMBOL").equalsIgnoreCase(symbol)) {
                return row.toCsvString();
            }
        }
        return "Symbol not found";
    }

}
