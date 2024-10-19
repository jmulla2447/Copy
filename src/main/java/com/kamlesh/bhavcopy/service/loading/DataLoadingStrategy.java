package com.kamlesh.bhavcopy.service.loading;

public interface DataLoadingStrategy {
    Object handleQuery(String queryType, String[] params);

    void loading();
}
