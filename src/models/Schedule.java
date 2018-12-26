package models;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Day> days = new ArrayList<>();

    public void Schedule() {

    }




    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }
}
