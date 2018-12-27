package models;

import java.util.ArrayList;

public class Professor {

    private String name;
    private ArrayList<Period> availabilityPeriods;

    public Professor(String name, ArrayList<Period> availabilityPeriods) {
        this.name = name;
        this.availabilityPeriods = availabilityPeriods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Period> getAvailabilityPeriods() {
        return availabilityPeriods;
    }

    public void setAvailabilityPeriods(ArrayList<Period> availabilityPeriods) {
        this.availabilityPeriods = availabilityPeriods;
    }
}
