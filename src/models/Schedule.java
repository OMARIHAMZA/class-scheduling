package models;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Day> days = new ArrayList<>();
    private Resources resources;

    public Schedule(Resources resources) {
        this.resources = resources;
    }

    public void generateSchedule(Schedule currentSchedule, int currentDay, int currentPostition) {

        Lecture mLecture = getNextLecture(currentSchedule);

        while (mLecture != null) {

            if (calculateCost(currentSchedule, mLecture, currentDay, currentPostition) >= 0) {
                Lecture tempLecture = new Lecture(mLecture);
                tempLecture.setStartTime(currentPostition);
                tempLecture.setEndTime(currentPostition + 2);
                tempLecture.setStage(null);
                currentSchedule.getDays().get(currentDay).getLectures().add(tempLecture);
                generateSchedule();
                break;
            }

//            mLecture = getNextLecture()
        }


    }

    private int calculateCost(Schedule currentSchedule, Lecture currentLecture, int currentDay, int currentPosition) {
        int cost = 0;


        return cost;
    }

    private Stage getNextStage(Schedule schedule) {
        Stage resultStage = null;
        for (Stage stage : resources.getStages()) {

        }
    }

    private Lecture getNextLecture(Schedule schedule) {
        for (Lecture lecture : resources.getLectures()) {
            if (!containsLecture(schedule, lecture)) {
                return lecture;
            }
        }
        return null;
    }

    private boolean containsLecture(Schedule schedule, Lecture lecture) {
        for (Day day : schedule.getDays()) {
            for (int i = 0; i < day.getLectures().size(); i++) {
                Lecture mLecture = day.getLectures().get(i);
                if (mLecture.getCourseName().equalsIgnoreCase(lecture.getCourseName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containtsStage(Schedule schedule, Stage stage, Lecture mLecture, int currentPosition) {
        boolean containsStage = false;
        for (Day day : schedule.getDays()) {
            if (day.getLectures().get(currentPosition) != null &&
                    day.getLectures().get(currentPosition).getStage().getName().equalsIgnoreCase(stage.getName())) {
                containsStage = true;
            }
        }
        return containsStage;
    }

    private Lecture getLectureByPosition(int position) {
        return resources.getLectures().get(position);
    }

    public Resources getResources() {
        return resources;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }
}
