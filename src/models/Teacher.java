package models;

import java.util.ArrayList;

public class Teacher  {

    private String name;
    private String courseName;
    private ArrayList<Period> availabilityPeriods;
    private ArrayList<String> groups;

    public Teacher(String name, String courseName, ArrayList<Period> availabilityPeriods, ArrayList<String> groups) {
        this.name = name;
        this.courseName = courseName;
        this.availabilityPeriods = availabilityPeriods;
        this.groups = groups;
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

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
