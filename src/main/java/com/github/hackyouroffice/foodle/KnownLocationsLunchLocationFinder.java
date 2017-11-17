package com.github.hackyouroffice.foodle;

import java.util.*;

public class KnownLocationsLunchLocationFinder implements LocationFinder {

  private final Set<Location> locations;

  public KnownLocationsLunchLocationFinder(){
    this.locations = new HashSet<>();
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
  public Set<Location> findLocations() {
    return locations;
  }
}
