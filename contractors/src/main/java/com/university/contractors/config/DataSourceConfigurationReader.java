package com.university.contractors.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

@Component
@PropertySource("classpath:datasource.properties")
public class DataSourceConfigurationReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigurationReader.class);

    private static final String URL_PROP_KEY = "database.url";
    private static final String PORT_PROP_KEY = "database.port";
    private static final String DATABASE_NAME_PROP_KEY = "database.name";
    private static final String USERNAME_PROP_KEY = "database.username";
    private static final String PASSWORD_PROP_KEY = "database.password";

    private static final String URL_PREFIX = "jdbc:sqlserver://";
    private static final String DATASOURCE_FILE_NAME = "datasource.properties";

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.url}")
    private String url;

    @Value("${database.port}")
    private String port;

    @Value("${database.name}")
    private String databaseName;

    @Value("${spring.datasource.driverClassName}")
    private String driverName;

    @PostConstruct
    private void readDataSourceProperties() {
        tryToReadExternalProperties();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    String getUrl() {
        return URL_PREFIX + url + ":" + port + ";databaseName=" + databaseName;
    }

    String getDriverName() {
        return driverName;
    }

    private void tryToReadExternalProperties() {
        final String datasourcePropertiesPath = getExternalDatasourcePropertiesPath();
        try {
            final FileInputStream inputStream = new FileInputStream(datasourcePropertiesPath);
            final Properties properties = new Properties();

            properties.load(inputStream);

            this.url = properties.getProperty(URL_PROP_KEY);
            this.port = properties.getProperty(PORT_PROP_KEY);
            this.databaseName = properties.getProperty(DATABASE_NAME_PROP_KEY);
            this.username = properties.getProperty(USERNAME_PROP_KEY);
            this.password = properties.getProperty(PASSWORD_PROP_KEY);

            LOGGER.info("Datasource configuration was read from external file: " + datasourcePropertiesPath);
        } catch (IOException e) {
            LOGGER.error("External datasource configuration file was no found in path: '" + datasourcePropertiesPath + "'", e);
        }
    }

    private static String getExternalDatasourcePropertiesPath() {
        final URL url = DataSourceConfiguration.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            String string = URLDecoder.decode(url.getPath(), "UTF-8");
            final File file = new File(string);
            final File parentFile = file.getParentFile().getParentFile().getParentFile();
            final String parentDirectoryPathWithFilePrefix = parentFile.getPath();
            final String parentDirectoryPath = StringUtils.substring(parentDirectoryPathWithFilePrefix, parentDirectoryPathWithFilePrefix.indexOf(":") + 1);
            return parentDirectoryPath + File.separator + DATASOURCE_FILE_NAME;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
