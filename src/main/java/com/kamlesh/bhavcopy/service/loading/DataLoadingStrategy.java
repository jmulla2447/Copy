package com.kamlesh.bhavcopy.service.loading;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public interface DataLoadingStrategy {
    Object handleQuery(String queryType, String[] params);

    void loading() throws IOException, CsvValidationException;
}
