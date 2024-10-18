package com.kamlesh.bhavcopy;

import com.kamlesh.bhavcopy.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BhavCopyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BhavCopyApplication.class, args);
    }

}
