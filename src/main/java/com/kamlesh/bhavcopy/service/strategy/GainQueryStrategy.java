package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GainQueryStrategy implements QueryStrategy {

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        double n = Double.parseDouble(params[0]);  // Assuming first parameter is N
        List<String> result = new ArrayList<>();

        for (CsvRecord row : records) {
            try {

                double open = Double.parseDouble(row.getField("OPEN_PRICE"));
                double close = Double.parseDouble(row.getField("CLOSE_PRICE"));
                double gain = ((close - open) / open) * 100;

                if (gain > n) {
                    result.add(row.getField("SYMBOL"));
                }
            } catch (NumberFormatException e) {
                //TODO: Print exception log here
                continue;
            }
        }
        return result;
    }
}
