package com.kamlesh.bhavcopy.service.loading;

import com.kamlesh.bhavcopy.dao.RecordDao;
import com.kamlesh.bhavcopy.service.factory.QueryContext;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DatabaseDataLoading implements DataLoadingStrategy {

    private final RecordDao dao;
    private final QueryContext queryContext;

    @Autowired
    public DatabaseDataLoading(RecordDao dao, QueryContext queryContext) {
        this.dao = dao;
        this.queryContext = queryContext;
    }

    @Override
    public Object handleQuery(String queryType, String[] params) {
        return dao.handleQuery(queryType, params);
    }

    @Override
    public void loading() {
        try {
            dao.loadCsvFile(queryContext.getRecords());
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
