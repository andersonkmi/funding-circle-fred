package com.fundingcircle.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:fred.properties")
public class FederalReserveRepositoryConfig {
    @Autowired
    Environment environment;

    private final String GDPC_URL = "gdpc_url";
    private final String UMCSENT_URL = "umcsent_url";
    private final String UNRATE_URL = "unrate_url";

    public String getGdpcUrl() {
        return environment.getProperty(GDPC_URL);
    }

    public String getUmcSentUrl() {
        return environment.getProperty(UMCSENT_URL);
    }

    public String getUnrateUrl() {
        return environment.getProperty(UNRATE_URL);
    }
}
