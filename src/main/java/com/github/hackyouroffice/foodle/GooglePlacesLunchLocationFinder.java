package com.github.hackyouroffice.foodle;

import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.maps.DistanceMatrixApi.newRequest;
import static com.google.maps.PlacesApi.nearbySearchQuery;

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

        try {
            return Arrays.stream(nearbySearchQuery(geoApiContext, latLng)
                    .radius(radius) // Meter
                    .type(PlaceType.RESTAURANT)
                    .await()
                    .results)
                    .map(this::mapSearchResultToLocation)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.info("Fehler beim Abrufen der GooglePlaces, reason: ",e);
            return Collections.emptyList();
        }

    }

    private Location mapSearchResultToLocation(PlacesSearchResult result){
        boolean openNow = true;
        if(result.openingHours != null && !result.openingHours.openNow){
            openNow = false;
        }
        return new Location(result.name, result.vicinity, 30, openNow, result.permanentlyClosed);
    }

    public void calculateDistanceToLocation(Location location) {

        try {

            Optional<DistanceMatrixRow> firstDistanceRow = getDistanceMatrixRow(location);
            setWayTimeIfApplicable(location, firstDistanceRow);

        } catch (Exception ex) {
            logger.warn("Es konnte keine Dauer für die Reise zum Restaurant berechnet werden, Message: ", ex.getMessage());
        }
    }

    private void setWayTimeIfApplicable(Location location, Optional<DistanceMatrixRow> firstDistanceRow) {

        firstDistanceRow.ifPresent(row -> {
            Optional<DistanceMatrixElement> firstElement = Arrays.stream(row.elements).findFirst();
            firstElement.ifPresent(element-> location.setWayTimeInSeconds(element.durationInTraffic.inSeconds));
        });
    }

    private Optional<DistanceMatrixRow> getDistanceMatrixRow(Location location) throws com.google.maps.errors.ApiException, InterruptedException, java.io.IOException {

        return Arrays.stream(newRequest(geoApiContext)
                        .origins(latLng)
                        .destinations(location.getAddress())
                        .mode(TravelMode.WALKING)
                        .departureTime(DateTime.now())
                        .await()
                        .rows)
                        .findFirst();
    }

}
