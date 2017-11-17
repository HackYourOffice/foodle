package com.github.hackyouroffice.foodle;

import com.github.hackyouroffice.foodle.storage.LocationsDynamoDbClient;
import com.github.hackyouroffice.foodle.storage.ProposedLocation;
import com.github.hackyouroffice.foodle.storage.ProposedLocationDataItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LunchProposer {

    private static final Logger LOG = LoggerFactory.getLogger(LunchProposer.class);

    private final GooglePlacesLunchLocationFinder googlePlacesLunchLocationFinder;
    private final KnownLocationsLunchLocationFinder knownLocationsLunchLocationFinder;
    private final LocationsDynamoDbClient locationsDynamoDbClient;
    private final Clock clock;

    public LunchProposer(GooglePlacesLunchLocationFinder googlePlacesLunchLocationFinder, KnownLocationsLunchLocationFinder knownLocationsLunchLocationFinder, LocationsDynamoDbClient locationsDynamoDbClient, Clock clock) {
        this.googlePlacesLunchLocationFinder = googlePlacesLunchLocationFinder;
        this.knownLocationsLunchLocationFinder = knownLocationsLunchLocationFinder;
        this.locationsDynamoDbClient = locationsDynamoDbClient;
        this.clock = clock;
    }

    public Proposal getProposal() {

        List<Location> locations = new ArrayList<>();

        locations.addAll(knownLocationsLunchLocationFinder.findLocations());
        locations.addAll(googlePlacesLunchLocationFinder.findLocations());

        final List<ProposedLocation> blacklist =
            locationsDynamoDbClient.findAll()
                .map(ProposedLocationDataItem::getProposedLocation)
                .sorted().limit(5)
                .collect(Collectors.toList());

        locations = locations.stream()
            .filter(new RecentlyProposedPredicate(blacklist)).collect(Collectors.toList());

        Collections.shuffle(locations);

        final Location selectedLocation = locations.get(0);

        LOG.info("Selected {} as location proposal.", selectedLocation);

        final ProposedLocationDataItem proposedLocationDataItem = new ProposedLocationDataItem();
        proposedLocationDataItem.setProposedLocation(new ProposedLocation(clock.millis(), selectedLocation));
        proposedLocationDataItem.setName(selectedLocation.getName());
        locationsDynamoDbClient.saveItem(proposedLocationDataItem);

        return new Proposal("Essensvorschlag", selectedLocation.getName());
    }
}
