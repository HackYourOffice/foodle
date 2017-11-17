package com.github.hackyouroffice.foodle;

import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GooglePlacesLunchLocationFinder implements LocationFinder {

    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesLunchLocationFinder.class);
    private final GeoApiContext geoApiContext;
    private final LatLng latLng;
    private final int radius;

    public GooglePlacesLunchLocationFinder(FoodleProperties foodleProperties) {
        geoApiContext = new GeoApiContext.Builder()
                .apiKey(foodleProperties.getGoogleMapsApiKey())
                .build();

        latLng = new LatLng(foodleProperties.getFoodleLocationLat(), foodleProperties.getFoodleLocationLng());
        radius = foodleProperties.getFoodleLocationRadius();
    }

    @Override
    public List<Location> findLocations() {
        NearbySearchRequest request =
                PlacesApi.nearbySearchQuery(geoApiContext, latLng)
                        .openNow(true)
                        .radius(radius) // Meter
                        .type(PlaceType.FOOD, PlaceType.RESTAURANT);

        PlacesSearchResult[] results;

        try {
            results = request.await().results;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        logger.info(String.format("Anzahl des Ergebnisses: %d", results.length));

        return Arrays.stream(results).map(x -> new Location(x.name)).collect(Collectors.toList());
    }
}
