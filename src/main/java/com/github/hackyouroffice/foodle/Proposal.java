package com.github.hackyouroffice.foodle;

import java.io.Serializable;

public class Proposal implements Serializable{

    private final String title;
    private final Location location;
    private String randomProposalPrefix;

    public Proposal(String title, Location location, String randomProposalPrefix) {
        this.title = title;
        this.location = location;
        this.randomProposalPrefix = randomProposalPrefix;
    }

    public String getTitle() {
        return title;
    }

    public String getSpeechText() {
        return randomProposalPrefix + location.getName();
    }

    public String getWayTimeInfoText() {
        return location.getWayTimeInfoText();
    }

    public String getCompleteLunchDuration() {
        return location.getCompleteLunchDuration();
    }
}
