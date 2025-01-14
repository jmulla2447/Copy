package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopbotQueryStrategy implements QueryStrategy {

    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        double n = Double.parseDouble(params[0]);
        List<String> result = new ArrayList<>();

        for (CsvRecord row : records) {
            try {
                double high = Double.parseDouble(row.getField("HIGH_PRICE"));
                double low = Double.parseDouble(row.getField("LOW_PRICE"));
                double topbot = ((high - low) / low) * 100;

                if (topbot > n) {
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
