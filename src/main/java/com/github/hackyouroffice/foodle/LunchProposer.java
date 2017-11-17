package com.github.hackyouroffice.foodle;

import java.util.HashSet;
import java.util.Set;

public class LunchProposer {

  private final GooglePlacesLunchLocationFinder googlePlacesLunchLocationFinder;
  private final KnownLocationsLunchLocationFinder knownLocationsLunchLocationFinder;

  public LunchProposer(){
    FoodleProperties properties = new FoodleProperties();
    this.googlePlacesLunchLocationFinder = new GooglePlacesLunchLocationFinder(properties);
    this. knownLocationsLunchLocationFinder = new KnownLocationsLunchLocationFinder();
  }

  public Proposal getProposal(){
      Set<Location> locations = knownLocationsLunchLocationFinder.findLocations();
      locations.addAll(googlePlacesLunchLocationFinder.findLocations());
      // TODO get random location from all locations, pick one and return proposal
      return new Proposal("Döner","immer gibt es döner.");
  }
}
