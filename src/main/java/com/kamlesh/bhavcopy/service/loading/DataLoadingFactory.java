package com.kamlesh.bhavcopy.service.loading;

import com.kamlesh.bhavcopy.config.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoadingFactory {

    private final InMemoryDataLoading inMemoryStrategy;
    private final DatabaseDataLoading databaseStrategy;
    private final ApplicationConfig config;

    @Autowired
    public DataLoadingFactory(
            InMemoryDataLoading inMemoryStrategy,
            DatabaseDataLoading databaseStrategy,
            ApplicationConfig applicationConfig) {
        this.inMemoryStrategy = inMemoryStrategy;
        this.databaseStrategy = databaseStrategy;
        this.config = applicationConfig;
    }

    public DataLoadingStrategy getStrategy() {
        if (config.isEnabled()) {
            return databaseStrategy;
        } else {
            return inMemoryStrategy;
        }
    }
}
