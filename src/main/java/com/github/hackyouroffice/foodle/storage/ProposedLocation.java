package com.github.hackyouroffice.foodle.storage;

import com.github.hackyouroffice.foodle.Location;

public class ProposedLocation implements Comparable<ProposedLocation> {

    private Long timestamp;
    private Location location;

    public ProposedLocation(Long timestamp, Location location) {
        this.timestamp = timestamp;
        this.location = location;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public int compareTo(ProposedLocation o) {
        // reverse sorting
        return Long.compare(o.getTimestamp(), timestamp);
    }
}
