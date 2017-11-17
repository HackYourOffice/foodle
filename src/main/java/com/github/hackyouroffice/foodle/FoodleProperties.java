package com.github.hackyouroffice.foodle;

import java.io.IOException;
import java.util.Properties;

public class FoodleProperties {

    private final Properties properties;

    public FoodleProperties() {

        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("foodle.properties"));

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
}
