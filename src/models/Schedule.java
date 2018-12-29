package models;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Schedule implements Comparable<Schedule> {

    private ArrayList<Day> days = new ArrayList<>();
    private int cost;
    private PriorityQueue<Schedule> schedules = new PriorityQueue<>();
    public int counter = 0;

    private Resources resources;

    public Schedule() {
    }

    public Schedule(Resources resources) {
        this.resources = resources;
        this.cost = 0;
        days.add(new Day("Sunday"));
        days.add(new Day("Monday"));
        days.add(new Day("Tuesday"));
        days.add(new Day("Wednesday"));
        days.add(new Day("Thursday"));
    }


    public Schedule generateSchedule(Schedule currentSchedule, int currentDay, int currentPosition) {

        while (!isComplete(currentSchedule)) {
            for (Lecture lecture : resources.getLectures()) {
                Stage mStage = getNextStage(currentSchedule, lecture, currentDay, currentPosition, lecture.isPractical());
                if (mStage != null) {
                    if (calculateCost(currentSchedule, lecture, currentDay, currentPosition) >= 0) {
                        Lecture tempLecture = new Lecture(lecture);
                        tempLecture.setStartTime(8 + (currentPosition ));
                        tempLecture.setEndTime(tempLecture.getStartTime() + 2);
                        tempLecture.setStage(mStage);
                        currentSchedule.getDays().get(currentDay).getLectures().add(tempLecture);
                        schedules.add(currentSchedule);
                    }
                }
            }
            if (currentDay < 5 && currentPosition < 7) {
                currentPosition += 1;
            } else if (currentDay < 4) {
                currentDay += 1;
                currentPosition = 0;
            }
            if (!schedules.isEmpty())
                currentSchedule = schedules.poll();
            else if (currentDay == 5) {
                return null;
            }
        }

        return currentSchedule;
    }


    private boolean isComplete(Schedule schedule) {
        int lecturesCount = 0;

        for (Day day : schedule.getDays()) {
            lecturesCount += day.getLectures() == null ? 0 : day.getLectures().size();
        }

        return lecturesCount == resources.getLectures().size();
    }


    private boolean lectureExists(Lecture currentLecture, int currentDay, int currentPosition) {
        if (!currentLecture.isPractical()) return true;
        Day mDay = days.get(currentDay);
        for (Lecture lecture : mDay.getLectures()) {
            if (lecture.getStartTime() == 8 + (currentPosition * 2)
                    && lecture.getEndTime() == 10 + (currentPosition * 2)
                    && !currentLecture.getTeacher().getName().equalsIgnoreCase(lecture.getTeacher().getName())) {
                return true;
            }
        }
        return false;
    }

    private int calculateCost(Schedule currentSchedule, Lecture newLecture, int currentDay, int currentPosition) {
        int cost = 0;

        if (!isLecturerAvailable(newLecture, currentDay, currentPosition, newLecture.isPractical())) {
            return -1;
        }


        for (Day day : currentSchedule.days) {
            for (Lecture oldLecture : day.getLectures()) {
                //Check Duplication
                if (oldLecture.isPractical() == newLecture.isPractical() && !oldLecture.isPractical()) {
                    //Theoretical
                    if (oldLecture.getProfessor().getName().equalsIgnoreCase(newLecture.getProfessor().getName())
                            && oldLecture.getCourseName().equalsIgnoreCase(newLecture.getCourseName())) {
                        return -1;
                    }
                } else if (oldLecture.isPractical() == newLecture.isPractical() && oldLecture.isPractical()) {
                    //Practical
                    if (oldLecture.getTeacher().getName().equalsIgnoreCase(newLecture.getTeacher().getName())
                            && oldLecture.getCourseName().equalsIgnoreCase(newLecture.getCourseName())
                            && oldLecture.getGroup().equalsIgnoreCase(newLecture.getGroup())) {
                        return -1;
                    }
                }
            }
        }

        if (checkTimeConflict(currentSchedule, newLecture, currentDay, currentPosition)) return -1;

        return cost;
    }

    private boolean checkTimeConflict(Schedule currentSchedule, Lecture newLecture, int currentDay, int currentPosition) {
        try {
            for (Lecture currentLecture : currentSchedule.getDays().get(currentDay).getLectures()) {
                //            Lecture currentLecture = currentSchedule.getDays().get(currentDay).getLectures().get(currentSchedule.getDays().get(currentDay).getLectures().size() - 1);
                int startTime = 8 + (currentPosition );
                int endTime = startTime + 2;


                if (currentLecture.isPractical() == newLecture.isPractical() && newLecture.isPractical()
                    &&(!currentLecture.getTeacher().isInLab()||!newLecture.getTeacher().isInLab())) {
                    if (!currentLecture.getTeacher().isInLab()) {
                        String labGroup1 = "3", labGroup2 = "4";
                        if (currentLecture.getGroup().equalsIgnoreCase("1")) {
                            labGroup1 = "1";
                            labGroup2 = "2";
                        }
                        if (newLecture.getGroup().equalsIgnoreCase(labGroup1) || newLecture.getGroup().equalsIgnoreCase(labGroup2)) {
                            if ((currentLecture.getStartTime() < endTime
                                    && currentLecture.getStartTime() >= startTime)
                                    || (currentLecture.getEndTime() > startTime
                                    && currentLecture.getEndTime() <= endTime)) {
                                return true;
                            }
                        }

                    } else if (!newLecture.getTeacher().isInLab()) {
                        String labGroup1 = "3", labGroup2 = "4";
                        if (newLecture.getGroup().equalsIgnoreCase("1")) {
                            labGroup1 = "1";
                            labGroup2 = "2";
                        }
                        if (currentLecture.getGroup().equalsIgnoreCase(labGroup1) || currentLecture.getGroup().equalsIgnoreCase(labGroup2)) {
                            if ((currentLecture.getStartTime() < endTime
                                    && currentLecture.getStartTime() >= startTime)
                                    || (currentLecture.getEndTime() > startTime
                                    && currentLecture.getEndTime() <= endTime)) {
                                return true;
                            }
                        }
                    }
                }

                else if (currentLecture.isPractical() == newLecture.isPractical() && newLecture.isPractical()
                        && (currentLecture.getTeacher().getName().equalsIgnoreCase(newLecture.getTeacher().getName())
                        || currentLecture.getGroup().equalsIgnoreCase(newLecture.getGroup()))) {

                    if ((currentLecture.getStartTime() < endTime
                            && currentLecture.getStartTime() >= startTime)
                            || (currentLecture.getEndTime() > startTime
                            && currentLecture.getEndTime() <= endTime)) {
                        return true;
                    }
                }
                else if (currentLecture.isPractical() == newLecture.isPractical() && !newLecture.isPractical()) {
                    if ((currentLecture.getStartTime() < endTime
                            && currentLecture.getStartTime() >= startTime)
                            || (currentLecture.getEndTime() > startTime
                            && currentLecture.getEndTime() <= endTime)) {
                        return true;
                    }
                } else if (currentLecture.isPractical() != newLecture.isPractical()) {
                    if ((currentLecture.getStartTime() < endTime
                            && currentLecture.getStartTime() >= startTime)
                            || (currentLecture.getEndTime() > startTime
                            && currentLecture.getEndTime() <= endTime)) {
                        return true;
                    }
                }
            }
        } catch (Exception ignored) {

        }

        return false;
    }

    private boolean isLecturerAvailable(Lecture currentLecture, int currentDay, int currentPosition, boolean isPractical) {
        ArrayList<Period> periods;
        if (isPractical) {
            periods = currentLecture.getTeacher().getAvailabilityPeriods();
        } else {
            periods = currentLecture.getProfessor().getAvailabilityPeriods();
        }
        for (Period period : periods) {
            if (period.getDayName().equalsIgnoreCase(days.get(currentDay).getDayName())) {
                if (period.getStartTime() <= (float) (8 + (currentPosition )*1.0)
                        && period.getEndTime() >= (10 + (currentPosition ))*1.0) {
                    return true;
                }
            }
        }
        return false;
    }

    private Stage getNextStage(Schedule schedule, Lecture mLecture, int currentDay, int currentPosition, boolean isPractical) {
        int startTime = 8 + (currentPosition * 2);
        int endTime = startTime + 2;
        if (isPractical && mLecture.getTeacher().isInLab()) {
            for (Stage stage : resources.getLabs()) {
                boolean foundMatch = false;
                for (Lecture lecture : schedule.getDays().get(currentDay).getLectures()) {
                    if (lecture.getStartTime() == startTime
                            && lecture.getEndTime() == endTime
                            && lecture.getStage().getName().equals(stage.getName())) {
                        foundMatch = true;
                    }
                }
                if (!foundMatch) return stage;
            }
        } else {
            for (Stage stage : resources.getStages()) {
                boolean foundMatch = false;
                for (Lecture lecture : schedule.getDays().get(currentDay).getLectures()) {
                    if (lecture.getStartTime() == startTime
                            && lecture.getEndTime() == endTime
                            && lecture.getStage().getName().equals(stage.getName())) {
                        foundMatch = true;
                    }
                }
                if (!foundMatch) return stage;
            }
        }
        return null;
    }

    private Lecture getNextLecture(int currentDay, int currentPosition, Schedule schedule) {
       /* for (Lecture lecture : resources.getLectures()) {
            if (!containsThoeriticalLecture(schedule, lecture)
                    && isLecturerAvailable(lecture, currentDay, currentPosition, false)) {
                return lecture;
            }
        }
        for (Lecture lecture : resources.getLectures()) {
            if (!containsPracticalLecture(schedule, lecture)
                    && isLecturerAvailable(lecture, currentDay, currentPosition, true)) {
                return lecture;
            }
        }*/
        return null;
    }

    private boolean containsThoeriticalLecture(Schedule schedule, Lecture lecture) {
        for (Day day : schedule.getDays()) {
            for (int i = 0; i < day.getLectures().size(); i++) {
                Lecture mLecture = day.getLectures().get(i);
                if (mLecture.isPractical() == lecture.isPractical()
                        && mLecture.getCourseName().equalsIgnoreCase(lecture.getCourseName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsPracticalLecture(Schedule schedule, Lecture lecture) {
        for (Day day : schedule.getDays()) {
            for (int i = 0; i < day.getLectures().size(); i++) {
                Lecture mLecture = day.getLectures().get(i);
                if (mLecture.isPractical() == lecture.isPractical()
                        && mLecture.getCourseName().equalsIgnoreCase(lecture.getCourseName())
                        && mLecture.getTeacher().getName().equalsIgnoreCase(lecture.getTeacher().getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsStage(Schedule schedule, Stage stage, int currentDay, int currentPosition) {
        return false;
    }

    /*private Lecture getLectureByPosition(int position) {
        return resources.getTheoreticalLectures().get(position);
    }*/

    public Resources getResources() {
        return resources;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }


    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int compareTo(Schedule o) {
        return Integer.compare(this.getCost(), o.getCost());
    }

    public PriorityQueue<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(PriorityQueue<Schedule> schedules) {
        this.schedules = schedules;
    }
}

/* private Schedule generateSchedule(Schedule currentSchedule, int currentDay, int currentPosititon) {

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
                tempLecture.setStage(getNextStage(currentSchedule, tempLecture.isPractical()));
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
    }*/
