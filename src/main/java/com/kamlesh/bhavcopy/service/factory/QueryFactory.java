package com.kamlesh.bhavcopy.service.factory;


public interface QueryFactory {
    QueryContext getContext(String queryType);
}
