package com.github.hackyouroffice.foodle.places;

import com.google.maps.*;
import com.google.maps.model.LatLng;

import java.util.logging.Logger;

public class PlacesService {

    Logger logger = Logger.getLogger("PlacesService");

    private String apiKey = "abc";

    GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(apiKey)
            .build();

    public void getNearbyFoodPlaces() {
        logger.info(PlacesApi.nearbySearchQuery(context, new LatLng(49.010065,8.353095)).toString());
    }
}
