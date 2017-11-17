package com.github.hackyouroffice.foodle;

import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;

public class GooglePlacesLunchLocationFinder implements LocationFinder {

    private static final Logger logger = LoggerFactory.getLogger(GooglePlacesLunchLocationFinder.class);
    private final GeoApiContext geoApiContext;

    public GooglePlacesLunchLocationFinder(FoodleProperties foodleProperties) {
        geoApiContext = new GeoApiContext.Builder()
                .apiKey(foodleProperties.getGoogleMapsApiKey())
                .build();

    }

    @Override
    public Set<Location> findLocations() {
        NearbySearchRequest request =
                PlacesApi.nearbySearchQuery(geoApiContext, new LatLng(49.010065, 8.353095))
                        .openNow(true)
                        .radius(100) // Meter
                        .type(PlaceType.FOOD, PlaceType.RESTAURANT);

        PlacesSearchResult[] results;

        try {
            results = request.await().results;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

        logger.info(String.format("Anzahl des Ergebnisses: %d", results.length));

        Set<Location> resultSet = Collections.emptySet();

        for (PlacesSearchResult searchResult : results) {
            Location location = new Location(searchResult.name);

            resultSet.add(location);
        }

        return resultSet;
    }
}
