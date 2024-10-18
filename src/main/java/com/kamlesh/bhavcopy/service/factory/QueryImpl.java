package com.kamlesh.bhavcopy.service.factory;

import org.springframework.stereotype.Component;

@Component
public class QueryImpl implements QueryFactory {

    private final QueryContext context;

    public QueryImpl(QueryContext context) {
        this.context = context;
    }

    @Override
    public QueryContext getContext(String queryType) {
        QueryType type = QueryType.fromString(queryType);
        context.setStrategy(type.getStrategy());
        return context;
    }
}
