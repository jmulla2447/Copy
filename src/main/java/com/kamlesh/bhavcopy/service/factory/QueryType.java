package com.kamlesh.bhavcopy.service.factory;

import com.kamlesh.bhavcopy.service.strategy.*;

public enum QueryType {
    SYMBOL(new GenericQuery()),
    COUNT(new GenericCountQuery()),
    GAIN(new GainQueryStrategy()),
    TOPBOT(new TopbotQueryStrategy()),
    STDDEV(new StddevQueryStrategy());


    private final QueryStrategy strategy;

    QueryType(QueryStrategy strategy) {
        this.strategy = strategy;
    }

    public static QueryType fromString(String queryType) {
        try {
            return QueryType.valueOf(queryType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown query type: " + queryType);
        }
    }

    public QueryStrategy getStrategy() {
        return strategy;
    }
}
