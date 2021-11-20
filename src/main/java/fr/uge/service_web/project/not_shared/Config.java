package fr.uge.service_web.project.not_shared;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();

    static {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> properties.setProperty(entry.getKey(), entry.getValue()));
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
