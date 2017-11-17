package com.github.hackyouroffice.foodle;

import com.google.maps.*;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResult;
import com.google.maps.model.TravelMode;
import org.joda.time.DateTime;
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
        logger.info(String.format("Google API Key: %s", foodleProperties.getGoogleMapsApiKey()));

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
                        //.openNow(false)
                        .radius(radius) // Meter
                        .type(PlaceType.RESTAURANT);

        PlacesSearchResult[] results;

        try {
            results = request.await().results;
        } catch (Exception e) {
            logger.info("Die Liste von Google war leer");
            e.printStackTrace();
            return Collections.emptyList();
        }

        logger.info(String.format("Anzahl der Ergebnisse: %d", results.length));

        return Arrays.stream(results)
                .map(x -> new Location(x.name, x.formattedAddress, 3, true, false))
                .collect(Collectors.toList());
    }

    public void calculateDistanceToLocation(Location location) {
        LatLng from = latLng;
        String to = location.getAddress();
        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(geoApiContext)
                .origins(from)
                .destinations(to)
                .mode(TravelMode.WALKING)
                .departureTime(DateTime.now());

        try {
            location.setWayTime((int) (request.await().rows[0].elements[0].durationInTraffic.inSeconds / 60.0));
        } catch (Exception ex) {
            logger.warn("Es konnte keine Dauer für die Reise zum Restaurant berechnet werden, Message: ", ex.getMessage());
        }
    }

}
