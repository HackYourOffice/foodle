package com.github.hackyouroffice.foodle;

import java.util.Objects;

public class Location {

    private String name;
    private String address;
    private int averageMinutesForEating;
    private boolean openNow;
    private boolean permanentlyClosed;
    private int wayTime = -1;

    public Location(String name, String address, int averageMinutesForEating, boolean openNow, boolean permanentlyClosed) {
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

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAverageMinutesForEating() {
        return averageMinutesForEating;
    }

    public void setAverageMinutesForEating(int averageMinutesForEating) {
        this.averageMinutesForEating = averageMinutesForEating;
    }

    public String getWayTimeInfoText() {
        if (wayTime == -1) {
            return "Ich kenne den Weg leider nicht so genau und weiss nicht wie lange du brauchst.";
        }

        return String.format("Du wirst in etwa %d Minuten benötigen um dahin zu kommen. ", wayTime);
    }

    public void setWayTime(int wayTime) {
        this.wayTime = wayTime;
    }

    public String getTotalTimeNeededText() {
        int time = getAverageMinutesForEating();
        if (wayTime != -1) {
            time += 2 * wayTime;
        }

        return String.format("Normalerweise benötigst du dabei also insgesamt %d Minuten. ", time);
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
