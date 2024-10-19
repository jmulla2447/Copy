package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.service.loading.DataLoadingFactory;
import com.kamlesh.bhavcopy.service.loading.DataLoadingStrategy;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CopyService {

    private final DataLoadingFactory strategyFactory;

    private DataLoadingStrategy strategy;

    public CopyService(DataLoadingFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
        this.strategy = this.strategyFactory.getStrategy();
    }

    public void loadCopy() throws IOException, CsvValidationException {
        strategy.loading();
    }

    public Object handleQuery(String queryType, String[] params) {
        return strategy.handleQuery(queryType, params);
    }
}
