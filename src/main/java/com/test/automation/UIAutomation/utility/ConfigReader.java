package com.test.automation.UIAutomation.utility;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigReader {
    private static final Logger LOGGER = Logger.getLogger(ConfigReader.class.getName());
    private static final Properties properties = new Properties();

    // Static block to load properties
    static {
        loadProperties("src/main/resources/data.properties");
        loadProperties("src/main/resources/config.properties");
    }

    /**
     * Loads properties from a given file path into the global properties object.
     */
    private static void loadProperties(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading properties file: {0}", filePath);
        }
    }

    /**
     * Retrieves a property value from the loaded properties.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Retrieves an API endpoint and dynamically replaces path parameters.
     */
    public static String getEndpoint(String endpointKey, String... pathParams) {
        String endpoint = properties.getProperty(endpointKey);
        if (endpoint == null) {
            LOGGER.log(Level.WARNING, "Endpoint key not found: {0}", endpointKey);
            return null;
        }

        for (String param : pathParams) {
            endpoint = endpoint.replaceFirst("\\{.*?}", param);
        }

        String baseUrl = getProperty("base.url");
        return baseUrl != null ? baseUrl + endpoint : endpoint;
    }


   
    public static void main(String[] args) {
         // Prints API token and key from .env
    }
}
