package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;

import java.util.List;
import java.util.stream.Collectors;

public class StddevQueryStrategy implements QueryStrategy {
    @Override
    public Object execute(List<CsvRecord> records, String[] params) {
        String series = params[0];  // Assuming first parameter is the series
        List<CsvRecord> seriesRecords = records.stream()
                .filter(record -> record.getField("SERIES").equalsIgnoreCase(series)).toList();

        List<Double> closingVals = seriesRecords.stream()
                .map(record -> Double.parseDouble(record.getField("CLOSE_PRICE"))).collect(Collectors.toList());
        // seriesRecords.stream().map(r -> Double.parseDouble(r.getField("CLOSE_PRICE"))).reduce(Double::add);
        double sum = seriesRecords.stream().collect(Collectors.summingDouble(r -> Double.parseDouble(r.getField("CLOSE_PRICE"))));
        double average = sum / seriesRecords.size();

        double varSum = closingVals.stream().collect(Collectors.summingDouble(v -> (v - average) * (v - average)));
        double variance = varSum / closingVals.size();
        return Math.sqrt(variance);
    }

}
