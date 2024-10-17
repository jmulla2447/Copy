package com.kamlesh.bhavcopy.service.factory;

import com.kamlesh.bhavcopy.service.strategy.CountQueryStrategy;
import com.kamlesh.bhavcopy.service.strategy.GainQueryStrategy;
import com.kamlesh.bhavcopy.service.strategy.QueryStrategy;
import com.kamlesh.bhavcopy.service.strategy.SymbolQueryStrategy;

public class QueryImpl implements QueryFactory {

    @Override
    public QueryStrategy getStrategy(String queryType) {
        switch (queryType.toUpperCase()) {
            case "SYMBOL":
                return new SymbolQueryStrategy();
            case "COUNT":
                return new CountQueryStrategy();
            case "GAIN":
                return new GainQueryStrategy();
            // Add other strategies here as needed
            default:
                throw new IllegalArgumentException("Unknown query type: " + queryType);
        }
    }
}
