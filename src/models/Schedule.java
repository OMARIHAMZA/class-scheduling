package models;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Day> days = new ArrayList<>();
    private Resources resources;

    public Schedule() {
    }

    public Schedule(Resources resources) {
        this.resources = resources;
        days.add(new Day("Sunday"));
        days.add(new Day("Monday"));
        days.add(new Day("Tuesday"));
        days.add(new Day("Wednesday"));
        days.add(new Day("Thursday"));
    }

    private Schedule generateSchedule(Schedule currentSchedule, int currentDay, int currentPosititon) {

        Lecture mLecture = getNextLecture(currentDay, currentPosititon, currentSchedule);


        while (mLecture == null) {
            if (currentDay == 4) break;
            if (currentPosititon == 4 && currentDay < 5) {
                currentPosititon = 0;
                currentDay += 1;
            } else {
                currentPosititon += 1;
            }
            mLecture = getNextLecture(currentDay, currentPosititon, currentSchedule);
        }

        while (mLecture != null) {
            if (calculateCost(currentSchedule, mLecture, currentDay, currentPosititon) >= 0) {
                Lecture tempLecture = new Lecture(mLecture);
                tempLecture.setStartTime(8 + (currentPosititon * 2));
                tempLecture.setEndTime(tempLecture.getStartTime() + 2);
                tempLecture.setStage(getNextStage(currentSchedule));
                currentSchedule.getDays().get(currentDay).getLectures().add(tempLecture);
                currentPosititon += 1;
                if (currentSchedule.getDays().get(currentDay).getLectures().size() == 4) {
                    currentDay += 1;
                    currentPosititon = 0;
                }
                generateSchedule(currentSchedule, currentDay, currentPosititon);
                break;
            }
            mLecture = getNextLecture(currentDay, currentPosititon, currentSchedule);
            if (mLecture == null && currentDay < 5) {
                currentDay++;
                currentPosititon = 0;
                mLecture = getNextLecture(currentDay, currentPosititon, currentSchedule);
            }
        }
        return currentSchedule;
    }

    public Schedule generateSchedule() {
        return generateSchedule(this, 0, 0);
    }

    private int calculateCost(Schedule currentSchedule, Lecture currentLecture, int currentDay, int currentPosition) {
        int cost = 0;

        /*if () {
            return -1;
        }*/

        return cost;
    }

    private boolean isDoctorAvailable(Lecture currentLecture, int currentDay, int currentPosition) {
        ArrayList<Period> periods = currentLecture.getLecturer().getAvailabilityPeriods();
        for (Period period : periods) {
            if (period.getDayName().equalsIgnoreCase(days.get(currentDay).getDayName())) {
                if (period.getStartTime() <= (float) (8 + (currentPosition * 2))
                        && period.getEndTime() >= (10 + (currentPosition * 2))) {
                    return true;
                }
            }
        }
        return false;
    }

    private Stage getNextStage(Schedule schedule) {
        return resources.getStages().get(0);
    }

    private Lecture getNextLecture(int currentDay, int currentPosition, Schedule schedule) {
        for (Lecture lecture : resources.getLectures()) {
            if (!containsLecture(schedule, lecture)
                    && isDoctorAvailable(lecture, currentDay, currentPosition)) {
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

    private boolean containsStage(Schedule schedule, Stage stage, int currentDay, int currentPosition) {
        return false;
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
