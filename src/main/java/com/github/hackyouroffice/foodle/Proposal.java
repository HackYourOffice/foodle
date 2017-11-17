package com.github.hackyouroffice.foodle;

public class Proposal {

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

    public String getAverageMinutesForEating() {
        return "Essen da dauert so lange: " + location.getAverageMinutesForEating();
    }
}
