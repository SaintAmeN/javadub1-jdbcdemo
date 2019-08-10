package com.javadub1.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
public class JdbcSettings {
    private String jdbcHost;
    private String jdbcPort;
    private String jdbcDatabaseName;

    private String jdbcUsername;
    private String jdbcPassword;

    public JdbcSettings() throws IOException {
        loadProperties();
    }

    private void loadProperties() throws IOException, NullPointerException {
        InputStream propertiesStream = JdbcSettings.class.getResourceAsStream("/jdbc.properties");

        Properties properties = new Properties();
        properties.load(propertiesStream);

        jdbcHost = properties.getProperty("jdbc.connection.host");
        jdbcPort = properties.getProperty("jdbc.connection.port");
        jdbcDatabaseName = properties.getProperty("jdbc.connection.db");

        jdbcUsername = properties.getProperty("jdbc.auth.username");
        jdbcPassword = properties.getProperty("jdbc.auth.password");
    }
}
