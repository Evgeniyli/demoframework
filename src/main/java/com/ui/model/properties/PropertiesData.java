package com.ui.model.properties;

import com.ui.core.exceptions.ExceptionError;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.ui.model.providers.DataProviders.getEnvValue;

public class PropertiesData {

    private static Map<String, String> settings;

    private static Properties settingsProperty;
    private static Properties serverProperty;

    public static String getBrowser() {
        return getValue("browser");
    }

    public static String getHubServer() {
        return getValue("server_name");
    }

    public static String ENVIRONMENT_URL = getEnvValue("url", "url_env");

    /**
     * Get value by id key
     *
     * @param key is set value
     * @return value by key
     */
    public static String getValue(String key) {
        if (settings == null) {
            if (settingsProperty == null) {
                initializeProperties();
            }
            setSettings();
        }
        return settings.get(key);
    }


    private static void initializeProperties() {
        settingsProperty = new Properties();
        serverProperty = new Properties();
        try {
            settingsProperty.load(PropertiesData.class.getClassLoader()
                    .getResourceAsStream("settings/settings.properties"));
            serverProperty.load(PropertiesData.class.getClassLoader()
                    .getResourceAsStream("properties/selenium.server.properties"));
        } catch (IOException e) {
            throw new ExceptionError("Property initialization error, ", e.getCause());
        }
    }

    private static void setSettings() {
        settings = new HashMap<>();
        setCommonSettings();
    }

    private static void setCommonSettings() {
        settings.put("server_name", serverProperty.getProperty(settingsProperty.getProperty("server")));
        settings.put("browser", settingsProperty.getProperty("browser"));
    }
}