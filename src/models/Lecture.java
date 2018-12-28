package models;

public class Lecture {

    private String courseName;
    private Professor professor;
    private Teacher teacher;
    private String group;
    private boolean isPractical = false;
    private Stage stage;
    private float startTime, endTime;

    public Lecture(String courseName, Professor professor) {
        this.courseName = courseName;
        this.professor = professor;
        this.group = "_";
    }

    public Lecture(String courseName, String group, Teacher teacher) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.isPractical = true;
        this.group = group;
    }

    public Lecture(Lecture lecture) {
        this.courseName = lecture.courseName;
        this.professor = lecture.professor;
        this.teacher = lecture.teacher;
        this.group = lecture.group;
        this.isPractical = lecture.isPractical;
        this.stage = lecture.stage;
        this.startTime = lecture.startTime;
        this.endTime = lecture.endTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isPractical() {
        return isPractical;
    }

    public void setPractical(boolean practical) {
        isPractical = practical;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
