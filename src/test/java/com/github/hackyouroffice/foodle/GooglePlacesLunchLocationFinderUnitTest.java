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
@Ignore
public class GooglePlacesLunchLocationFinderUnitTest {

    FoodleProperties properties = mock(FoodleProperties.class);
    GooglePlacesLunchLocationFinder finder;

    @Test
    public void findLocations() {
        when(properties.getGoogleMapsApiKey()).thenReturn("APIKEY");
        when(properties.getFoodleLocationLat()).thenReturn(49.011750);
        when(properties.getFoodleLocationLng()).thenReturn(8.350571);
        when(properties.getFoodleLocationRadius()).thenReturn(500);

        finder = new GooglePlacesLunchLocationFinder(properties);

        List<Location> locations = finder.findLocations();

        assertThat(locations.isEmpty(), is(false));
    }
}