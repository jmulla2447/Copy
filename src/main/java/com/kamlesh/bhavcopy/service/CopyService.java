package com.kamlesh.bhavcopy.service;

import com.kamlesh.bhavcopy.service.factory.QueryContext;
import com.kamlesh.bhavcopy.service.factory.QueryFactory;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CopyService {

    private final QueryContext queryContext;
    private final QueryFactory queryFactory;

    public CopyService(QueryContext queryContext, QueryFactory queryFactory) {
        this.queryContext = queryContext;
        this.queryFactory = queryFactory;
    }

    public void loadCopy() throws IOException, CsvValidationException {
        queryContext.loadCsvFile();
    }

    public Object handleQuery(String queryType, String[] params) {
        // Get the strategy from the factory
        QueryContext context = queryFactory.getContext(queryType);

        // Execute the strategy
        return context.executeQuery(params);
    }
}
