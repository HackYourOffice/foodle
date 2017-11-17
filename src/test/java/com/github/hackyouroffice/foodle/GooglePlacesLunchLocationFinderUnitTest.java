package com.github.hackyouroffice.foodle;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GooglePlacesLunchLocationFinderUnitTest {

    FoodleProperties properties = mock(FoodleProperties.class);
    GooglePlacesLunchLocationFinder finder;

    static final String API_KEY = "APIKEY";

    @Test
    @Ignore
    public void findLocations() {
        when(properties.getGoogleMapsApiKey()).thenReturn(API_KEY);
        when(properties.getFoodleLocationLat()).thenReturn(49.011750);
        when(properties.getFoodleLocationLng()).thenReturn(8.350571);
        when(properties.getFoodleLocationRadius()).thenReturn(500);

        finder = new GooglePlacesLunchLocationFinder(properties);

        List<Location> locations = finder.findLocations();

        assertThat(locations.isEmpty(), is(false));
    }

    @Ignore
    @Test
    public void calculateDistanceToLocation() {
        when(properties.getGoogleMapsApiKey()).thenReturn(API_KEY);
        when(properties.getFoodleLocationLat()).thenReturn(49.011750);
        when(properties.getFoodleLocationLng()).thenReturn(8.350571);
        when(properties.getFoodleLocationRadius()).thenReturn(500);

        Location location = new Location("die location", "Carl-Metz-Straße 7, 76185 Karlsruhe", 30, true);

        finder = new GooglePlacesLunchLocationFinder(properties);

        finder.calculateDistanceToLocation(location);

        assertThat((location.getWayTimeInMinutes() > 0), is(true));
    }
}
