package com.github.hackyouroffice.foodle;

import com.google.maps.*;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;

public class GooglePlacesLunchLocationFinder implements LocationFinder {

    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesLunchLocationFinder.class);
    private final GeoApiContext geoApiContext;

    public GooglePlacesLunchLocationFinder(FoodleProperties foodleProperties) {
        this.geoApiContext = new GeoApiContext.Builder()
                .apiKey(foodleProperties.getGoogleMapsApiKey())
                .build();

    }

    @Override
    public Set<Location> findLocations() {
        logger.info(PlacesApi.nearbySearchQuery(geoApiContext, new LatLng(49.010065, 8.353095)).toString());
        return Collections.emptySet();

    }
}
