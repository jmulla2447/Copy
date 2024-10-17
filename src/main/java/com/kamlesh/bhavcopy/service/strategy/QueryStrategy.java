package com.kamlesh.bhavcopy.service.strategy;

import com.kamlesh.bhavcopy.model.CsvRecord;

import java.util.List;


public interface QueryStrategy {
    Object execute(List<CsvRecord> records, String[] params);
}
