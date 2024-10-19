package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.service.loading.DataLoadingFactory;
import com.kamlesh.bhavcopy.service.loading.DataLoadingStrategy;
import org.springframework.stereotype.Service;

@Service
public class CopyService {

    private final DataLoadingFactory strategyFactory;

    private DataLoadingStrategy strategy;

    public CopyService(DataLoadingFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
        this.strategy = this.strategyFactory.getStrategy();
    }

    public void loadCopy() {
        strategy.loading();
    }

    public Object handleQuery(String queryType, String[] params) {
        return strategy.handleQuery(queryType, params);
    }
}
