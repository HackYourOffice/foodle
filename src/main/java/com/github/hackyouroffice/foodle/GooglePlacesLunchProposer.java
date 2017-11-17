package com.github.hackyouroffice.foodle;

import com.google.maps.*;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GooglePlacesLunchProposer implements LunchProposer {

    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesLunchProposer.class);
    private final GeoApiContext geoApiContext;

    public GooglePlacesLunchProposer(FoodleProperties foodleProperties) {
        this.geoApiContext = new GeoApiContext.Builder()
                .apiKey(foodleProperties.getGoogleMapsApiKey())
                .build();

    }

    @Override
    public Proposal propose() {
        logger.info(PlacesApi.nearbySearchQuery(geoApiContext, new LatLng(49.010065, 8.353095)).toString());
        return null;
    }
}
