package com.kamlesh.bhavcopy.service.factory;

import com.kamlesh.bhavcopy.service.strategy.QueryStrategy;
import org.springframework.stereotype.Component;

@Component
public class QueryImpl implements QueryFactory {

    @Override
    public QueryStrategy getStrategy(String queryType) {
        QueryType type = QueryType.fromString(queryType);
        return type.getStrategy();
    }
}
