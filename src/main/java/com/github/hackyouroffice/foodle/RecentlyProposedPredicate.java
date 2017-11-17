package com.github.hackyouroffice.foodle;

import com.github.hackyouroffice.foodle.storage.ProposedLocation;

import java.util.List;
import java.util.function.Predicate;

public class RecentlyProposedPredicate implements Predicate<Location> {

    private final List<ProposedLocation> blacklist;

    public RecentlyProposedPredicate(List<ProposedLocation> blacklist) {
        this.blacklist = blacklist;
    }

    @Override
    public boolean test(Location location) {

        return blacklist.stream()
            .anyMatch(proposedLocation -> proposedLocation.getLocation().equals(location));
    }
}
