package com.github.hackyouroffice.foodle;

public class Proposal {

    private final String title;

    private final Location location;

    public Proposal(String title, Location location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public Location getLocation() {
        return location;
    }
}
