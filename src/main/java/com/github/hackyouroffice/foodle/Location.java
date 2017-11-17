package com.github.hackyouroffice.foodle;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Location implements Serializable{

    private String name;
    private String address;
    private int averageMinutesForEating;
    private boolean openNow;
    private boolean permanentlyClosed;
    private long wayTimeInMinutes = -1;

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
        if (wayTimeInMinutes == -1) {
            return "Ich kenne den Weg leider nicht so genau und weiss nicht wie lange du brauchst.";
        }

        final String selectedText = "Du wirst in etwa %d Minuten benötigen um dahin zu kommen.";
        return String.format(selectedText, wayTimeInMinutes);
    }

    public void setWayTimeInSeconds(long wayTime) {
        wayTimeInMinutes = wayTime / 60;
    }

    public String getTotalTimeNeededText() {
        int time = getAverageMinutesForEating();
        if (wayTimeInMinutes != -1) {
            time += 2 * wayTimeInMinutes;
        }

        List<String> texts = Arrays.asList(
                "Normalerweise benötigst du dabei also insgesamt %d Minuten.",
                "Die durchschnittliche Dauer für dieses Mittagessen wird %d Minuten betragen."
        );

        Collections.shuffle(texts);

        final String selectedText = texts.get(0);

        return String.format(selectedText, time);
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
