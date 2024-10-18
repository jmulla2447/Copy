package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopbotQueryStrategy implements QueryStrategy {

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        double n = Double.parseDouble(params[0]);  // Assuming first parameter is N
        List<String> result = new ArrayList<>();

        for (CsvRecord record : records) {
            try {
                double high = Double.parseDouble(record.getField("HIGH_PRICE"));
                double low = Double.parseDouble(record.getField("LOW_PRICE"));
                double topbot = ((high - low) / low) * 100;

                if (topbot > n) {
                    result.add(record.getField("SYMBOL"));
                }
            } catch (NumberFormatException e) {
                //TODO: Print exception log here
                continue;
            }
        }
        return result;
    }
}
