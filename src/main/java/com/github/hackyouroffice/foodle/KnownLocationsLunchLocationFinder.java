package com.github.hackyouroffice.foodle;

import java.util.ArrayList;
import java.util.List;

public class KnownLocationsLunchLocationFinder implements LocationFinder {

    private final List<Location> locations;

    public KnownLocationsLunchLocationFinder() {
        this.locations = new ArrayList<>();
        this.locations.add(new Location("Ritter"));
        this.locations.add(new Location("Kaufland Asiat"));
        this.locations.add(new Location("Kaufland Döner"));
        this.locations.add(new Location("Anderer Döner"));
        this.locations.add(new Location("Papparazi"));
        this.locations.add(new Location("Curry76"));
        this.locations.add(new Location("Thai"));
        this.locations.add(new Location("Bäcker"));
        this.locations.add(new Location("Grieche"));
        this.locations.add(new Location("Lörz"));
    }

    @Override
    public List<Location> findLocations() {
        return locations;
    }
}
