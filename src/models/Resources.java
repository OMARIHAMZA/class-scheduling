package models;

import java.util.ArrayList;

public class Resources {

    private ArrayList<Lecture> lectures;
    private ArrayList<Lecturer> lecturers;
    private ArrayList<Stage> stages;

    public Resources(ArrayList<Lecture> lectures, ArrayList<Lecturer> lecturers, ArrayList<Stage> stages) {
        this.lectures = lectures;
        this.lecturers = lecturers;
        this.stages = stages;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }
}
