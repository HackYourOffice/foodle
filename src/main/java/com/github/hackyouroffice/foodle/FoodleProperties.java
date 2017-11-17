package com.github.hackyouroffice.foodle;

import java.io.IOException;
import java.util.Properties;

public class FoodleProperties {

    private final Properties properties;

    public FoodleProperties(){

       this.properties = new Properties();
       try {
            this.properties.load(this.getClass().getResourceAsStream("foodle.properties"));

        } catch (IOException e) {
           throw new RuntimeException("Foodle Properties could not be loaded: " + e.getMessage());
        }
    }

    public String getGoogleMapsApiKey(){
       return this.properties.getProperty("google.api.key");
    }
}
