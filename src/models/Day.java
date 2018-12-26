package models;

import java.util.ArrayList;

public class Day {

    private String dayName;
    private ArrayList<Lecture> lectures;

    public Day(String dayName, ArrayList<Lecture> lectures) {
        this.dayName = dayName;
        this.lectures = lectures;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }
}
