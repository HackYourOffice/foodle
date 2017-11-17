package com.github.hackyouroffice.foodle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KnownLocationsLunchProposer implements LunchProposer {

  private static final List<Location> locations = Arrays.asList(
      new Location("Ritter"),
      new Location("Kaufland Asiat"),
      new Location("Kaufland Döner"),
      new Location("Anderer Döner"),
      new Location("Papparazi"),
      new Location("Curry76"),
      new Location("Thai"),
      new Location("Bäcker"),
      new Location("Grieche")
  );

  @Override
  public Proposal propose() {

    Collections.shuffle(locations);

    final String locationName = locations.get(0).getName();

    return new Proposal("Essen!", locationName);
  }
}
