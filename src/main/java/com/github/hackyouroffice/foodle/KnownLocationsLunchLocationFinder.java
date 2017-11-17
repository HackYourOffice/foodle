package com.github.hackyouroffice.foodle;

import java.util.ArrayList;
import java.util.List;

public class KnownLocationsLunchLocationFinder implements LocationFinder {

    private final List<Location> locations;

    public KnownLocationsLunchLocationFinder() {
        locations = new ArrayList<>();

        String kauflandAdresse = "Carl-Metz-Straße 7, 76185 Karlsruhe";
        locations.add(new Location("Ritter", "Hardtstraße 25, 76185 Karlsruhe", 60, true));
        locations.add(new Location("Kaufland Asiat", kauflandAdresse, 20, true));
        locations.add(new Location("Kaufland Döner", kauflandAdresse, 20, true));
        locations.add(new Location("Anderer Döner", kauflandAdresse, 35, true));
        locations.add(new Location("Curry76", "Rheinstraße 28, 76185 Karlsruhe", 25, true));
        locations.add(new Location("Thai", "Eckenerstraße 1, 76185 Karlsruhe", 60, true));
        locations.add(new Location("Bäcker", kauflandAdresse, 5, true));
        locations.add(new Location("Grieche", "Am Sonnenbad 1, 76189 Karlsruhe", 60, true));
        locations.add(new Location("Lörz", "Hardtstraße 10, 76185 Karlsruhe", 5, true));
    }

    @Override
    public List<Location> findLocations() {
        return locations;
    }
}
