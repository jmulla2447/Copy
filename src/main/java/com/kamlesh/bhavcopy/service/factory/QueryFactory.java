package com.kamlesh.bhavcopy.service.factory;

import com.kamlesh.bhavcopy.service.strategy.QueryStrategy;
import org.springframework.stereotype.Component;

@Component
public interface  QueryFactory{
    QueryStrategy getStrategy(String queryType);
}
