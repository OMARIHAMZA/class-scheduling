package models;

import java.util.ArrayList;

public class Resources {

    private ArrayList<Lecture> theoreticalLectures;
    private ArrayList<Lecture> practicalLectures;
    private ArrayList<Professor> professors;
    private ArrayList<Stage> stages;

    public Resources(ArrayList<Lecture> theoreticalLectures, ArrayList<Lecture> practicalLectures, ArrayList<Professor> professors, ArrayList<Stage> stages) {
        this.theoreticalLectures = theoreticalLectures;
        this.practicalLectures = practicalLectures;
        this.professors = professors;
        this.stages = stages;
    }

    public ArrayList<Lecture> getTheoreticalLectures() {
        return theoreticalLectures;
    }

    public void setTheoreticalLectures(ArrayList<Lecture> theoreticalLectures) {
        this.theoreticalLectures = theoreticalLectures;
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

    public ArrayList<Lecture> getPracticalLectures() {
        return practicalLectures;
    }

    public void setPracticalLectures(ArrayList<Lecture> practicalLectures) {
        this.practicalLectures = practicalLectures;
    }
}
