package com.github.hackyouroffice.foodle;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable{

    private String name;
    private String address;
    private int averageMinutesForEating;
    private boolean openNow;
    private long wayTimeInMinutes = -1;

    public Location(String name, String address, int averageMinutesForEating, boolean openNow) {
        this.name = name;
        this.address = address;
        this.averageMinutesForEating = averageMinutesForEating;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getWayTimeInfoText() {
        if (wayTimeInMinutes == -1) {
            return "Ich kenne den Weg leider nicht so genau und weiss nicht wie lange du brauchst.";
        }

        final String selectedText = "Du wirst in etwa %d Minuten benötigen um dahin zu kommen.";
        return String.format(selectedText, wayTimeInMinutes);
    }

    public void setWayTimeInSeconds(long wayTime) {
        wayTimeInMinutes = wayTime / 60;
    }

    public String getCompleteLunchDuration() {
        int time = averageMinutesForEating;
        if (wayTimeInMinutes != -1) {
            time += 2 * wayTimeInMinutes;
        }

        return String.format("Normalerweise benötigst du dabei insgesamt %d Minuten zum Essen inklusive Weegzeit ", time);
    }

    public long getWayTimeInMinutes() {
        return wayTimeInMinutes;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
