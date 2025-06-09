/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package basarozkasli.infrastructure;

/**
 *
 * @author basar
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfiguration implements IConfiguration {
    private static AppConfiguration instance;
    private Properties properties;

    private AppConfiguration() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("config.properties dosyası okunamadı!", e);
        }
    }

    public static AppConfiguration getInstance() {
        if (instance == null) {
            instance = new AppConfiguration();
        }
        return instance;
    }

    @Override
    public String getDatabaseUrl() {
        return properties.getProperty("db.url");
    }

    @Override
    public String getDatabaseUsername() {
        return properties.getProperty("db.user");
    }

    @Override
    public String getDatabasePassword() {
        return properties.getProperty("db.password");
    }

    @Override
    public String getImageDirectory() {
        return properties.getProperty("image.dir");
    }

    @Override
    public int getMaxRating() {
        return Integer.parseInt(properties.getProperty("rating.max"));
    }

    @Override
    public int getMinRating() {
        return Integer.parseInt(properties.getProperty("rating.min"));
    }
}
