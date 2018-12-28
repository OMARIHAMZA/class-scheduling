package models;

import java.util.ArrayList;

public class Resources {

    private ArrayList<Lecture> lectures;
    private ArrayList<Professor> professors;
    private ArrayList<Stage> stages;
    private ArrayList<Stage> labs;

    public Resources(ArrayList<Lecture> lectures, ArrayList<Professor> professors, ArrayList<Stage> stages, ArrayList<Stage> labs) {
        this.lectures = lectures;
        this.professors = professors;
        this.stages = stages;
        this.labs = labs;
    }

    public ArrayList<Stage> getLabs() {
        return labs;
    }

    public void setLabs(ArrayList<Stage> labs) {
        this.labs = labs;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }
}
