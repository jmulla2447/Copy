package com.kamlesh.bhavcopy.service.loading;

import com.kamlesh.bhavcopy.service.factory.QueryContext;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class InMemoryDataLoading implements DataLoadingStrategy {

    private final QueryContext queryContext;

    @Autowired
    public InMemoryDataLoading(QueryContext queryContext) {
        this.queryContext = queryContext;
    }

    @Override
    public Object handleQuery(String queryType, String[] params) {
        return queryContext.executeQuery(params);
    }

    @Override
    public void loading() throws IOException, CsvValidationException {
        queryContext.loadCsvFile();
    }
}
