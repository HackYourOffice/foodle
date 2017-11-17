package com.github.hackyouroffice.foodle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FoodleProperties {

    private final Properties properties;

    public FoodleProperties() {

        properties = new Properties();
        String resourceName = "foodle.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException("Foodle Properties could not be loaded: " + e.getMessage());
        }
    }

    public String getGoogleMapsApiKey() {
        return properties.getProperty("google.api.key");
    }

    public double getFoodleLocationLat() {
        return Double.parseDouble(properties.getProperty("foodle.location.lat"));
    }

    public double getFoodleLocationLng() {
        return Double.parseDouble(properties.getProperty("foodle.location.lng"));
    }

    public int getFoodleLocationRadius() {
        return Integer.parseInt(properties.getProperty("foodle.location.radius"));
    }

    public String getAwsSecretKey() {
        return properties.getProperty("foodle.aws.secretkey");
    }

    public String getAwsAccessKey() {
        return properties.getProperty("foodle.aws.accesskey");
    }

}
