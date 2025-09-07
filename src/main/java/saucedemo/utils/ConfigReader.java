package saucedemo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties props;

    public static void loadProperties(String filePath) {
        props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
