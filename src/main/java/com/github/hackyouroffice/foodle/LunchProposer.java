package com.github.hackyouroffice.foodle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LunchProposer {

    private static final Logger LOG = LoggerFactory.getLogger(LunchProposer.class);

    private final GooglePlacesLunchLocationFinder googlePlacesLunchLocationFinder;
    private final KnownLocationsLunchLocationFinder knownLocationsLunchLocationFinder;

    public LunchProposer(GooglePlacesLunchLocationFinder googlePlacesLunchLocationFinder, KnownLocationsLunchLocationFinder knownLocationsLunchLocationFinder) {
        this.googlePlacesLunchLocationFinder = googlePlacesLunchLocationFinder;
        this.knownLocationsLunchLocationFinder = knownLocationsLunchLocationFinder;
    }

    public Proposal getProposal() {

        List<Location> locations = new ArrayList<>();

        locations.addAll(knownLocationsLunchLocationFinder.findLocations());
        locations.addAll(googlePlacesLunchLocationFinder.findLocations());

        Collections.shuffle(locations);

        final Location selectedLocation = locations.get(0);

        LOG.info("Selected {} as location proposal.", selectedLocation);

        return new Proposal("Essensvorschlag", selectedLocation.getName());
    }
}
