package models;

public class Lecture {

    private String courseName;
    private Lecturer lecturer;
    private Stage stage;
    private float startTime, endTime;

    public Lecture(String courseName, Lecturer lecturer) {
        this.courseName = courseName;
        this.lecturer = lecturer;
    }

    public Lecture(Lecture lecture) {
        this.courseName = lecture.courseName;
        this.lecturer = lecture.lecturer;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
