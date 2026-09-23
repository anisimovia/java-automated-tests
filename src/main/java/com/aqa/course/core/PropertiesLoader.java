package com.aqa.course.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
    private static final String CONFIG_PROPERTIES = "config.properties";
    private Properties properties;

    public String getProperty(String key){
        if (properties == null){
            properties = loadProp();
        }
        return properties.getProperty(key);
    }

    private Properties loadProp() {
        File file = new File(PropertiesLoader.class.getClassLoader().getResource(CONFIG_PROPERTIES).getFile());
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
