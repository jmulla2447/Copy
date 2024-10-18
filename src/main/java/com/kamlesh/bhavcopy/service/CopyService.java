package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.config.ApplicationConfig;
import com.kamlesh.bhavcopy.dao.RecordDao;
import com.kamlesh.bhavcopy.service.factory.QueryContext;
import com.kamlesh.bhavcopy.service.factory.QueryFactory;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CopyService {

    private final QueryContext queryContext;
    private final QueryFactory queryFactory;
    private final RecordDao dao;
    private ApplicationConfig config;

    public CopyService(QueryContext queryContext, QueryFactory queryFactory, RecordDao dao, ApplicationConfig applicationConfig) {
        this.queryContext = queryContext;
        this.queryFactory = queryFactory;
        this.dao = dao;
        this.config = applicationConfig;
    }

    public void loadCopy() throws IOException, CsvValidationException {
        if (config.isEnabled()) {
            dao.loadCsvFile(queryContext.getRecords());
        } else {
            queryContext.loadCsvFile();
        }
    }

    public Object handleQuery(String queryType, String[] params) {
        // Get the strategy from the factory
        QueryContext context = queryFactory.getContext(queryType);

        // Execute the strategy
        return context.executeQuery(params);
    }
}
