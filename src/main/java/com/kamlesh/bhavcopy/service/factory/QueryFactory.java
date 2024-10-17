package com.kamlesh.bhavcopy.service.factory;

import com.kamlesh.bhavcopy.service.strategy.QueryStrategy;


public interface QueryFactory {
    QueryStrategy getStrategy(String queryType);
}
