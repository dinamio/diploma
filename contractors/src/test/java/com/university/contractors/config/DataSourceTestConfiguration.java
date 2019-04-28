package com.university.contractors.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceTestConfiguration {

    private static final String H2_DB_URL = "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1";
    private static final String H2__DB_USERNAME = "sa";
    private static final String H2__DB_PASSWORD = "sa";
    private static final String H2__DB_DRIVER_NAME = "org.h2.Driver";

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(H2__DB_USERNAME)
                .password(H2__DB_PASSWORD)
                .url(H2_DB_URL)
                .driverClassName(H2__DB_DRIVER_NAME)
                .build();
    }
}
