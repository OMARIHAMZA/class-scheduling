package models;

import java.util.ArrayList;
import java.util.HashSet;
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
                counter++;
                Stage mStage = getNextStage(currentSchedule, lecture, currentDay, currentPosition, lecture.isPractical());
                if (mStage != null) {
                    if (constraints(currentSchedule, lecture, currentDay, currentPosition)) {
                        Lecture tempLecture = new Lecture(lecture);
                        tempLecture.setStartTime(8 + (currentPosition));
                        tempLecture.setEndTime(tempLecture.getStartTime() + 2);
                        tempLecture.setStage(mStage);
                        currentSchedule.getDays().get(currentDay).getLectures().add(tempLecture);
                        currentSchedule.cost = calculateCost(currentSchedule);
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

    private int calculateCost(Schedule currentSchedule) {
        int cost = 0;
        for (Day day : currentSchedule.days) {
            if (day.getLectures().size() == 0) { //Empty day
                cost -= 10;
            }
            float end = 0;
            for (Lecture lecture : day.getLectures()) {
                if (lecture.getStartTime() > end) { //gap between lectures
                    cost += 5;
                }
                end = lecture.getEndTime();
            }
            for (Lecture lecture : day.getLectures()) { // exist practical in more day
                if (lecture.isPractical()) {
                    cost++;
                    break;
                }
            }


        }


        return cost;
    }


    private boolean isComplete(Schedule schedule) {
        int lecturesCount = 0;

        for (Day day : schedule.getDays()) {
            lecturesCount += day.getLectures() == null ? 0 : day.getLectures().size();
        }

        return lecturesCount == resources.getLectures().size();
    }


    private boolean constraints(Schedule currentSchedule, Lecture newLecture, int currentDay, int currentPosition) {
        int cost = 0;

        if (!isLecturerAvailable(newLecture, currentDay, currentPosition, newLecture.isPractical())) {
            return false;
        }


        for (Day day : currentSchedule.days) {
            for (Lecture oldLecture : day.getLectures()) {
                //Check Duplication
                if (oldLecture.isPractical() == newLecture.isPractical() && !oldLecture.isPractical()) {
                    //Theoretical
                    if (oldLecture.getProfessor().getName().equalsIgnoreCase(newLecture.getProfessor().getName())
                            && oldLecture.getCourseName().equalsIgnoreCase(newLecture.getCourseName())) {
                        if (oldLecture.getLectuerCount() > 0 && oldLecture.getLectuerCount() != newLecture.getLectuerCount())
                            return true;
                         return false;
                    }
                } else if (oldLecture.isPractical() == newLecture.isPractical() && oldLecture.isPractical()) {
                    //Practical
                    if (oldLecture.getTeacher().getName().equalsIgnoreCase(newLecture.getTeacher().getName())
                            && oldLecture.getCourseName().equalsIgnoreCase(newLecture.getCourseName())
                            && oldLecture.getGroup().equalsIgnoreCase(newLecture.getGroup())) {
                        return false;
                    }
                }
            }
        }

        if (checkTimeConflict(currentSchedule, newLecture, currentDay, currentPosition)) return false;

        return true;
    }

    private boolean checkTimeConflict(Schedule currentSchedule, Lecture newLecture, int currentDay, int currentPosition) {
        try {
            for (Lecture currentLecture : currentSchedule.getDays().get(currentDay).getLectures()) {
                //   Lecture currentLecture = currentSchedule.getDays().get(currentDay).getLectures().get(currentSchedule.getDays().get(currentDay).getLectures().size() - 1);
                int startTime = 8 + (currentPosition);
                int endTime = startTime + 2;


                /** one in lab or two in lab*/
                if (currentLecture.isPractical() == newLecture.isPractical() && newLecture.isPractical()
                        && (!currentLecture.getTeacher().isInLab() || !newLecture.getTeacher().isInLab())) {
                    if (!currentLecture.getTeacher().isInLab()) {
                        String labGroup1 = "3S", labGroup2 = "4S";
                        //TODO G1 or G2
                        String sub=currentLecture.getGroup().substring(0,2);

                        if (sub.equalsIgnoreCase("G1")) {

                            labGroup1 = "1S";
                            labGroup2 = "2S";
                        }
                        if (newLecture.getGroup().equalsIgnoreCase(labGroup1) || newLecture.getGroup().equalsIgnoreCase(labGroup2)) {

                            if ((currentLecture.getStartTime() < endTime
                                    && currentLecture.getStartTime() >= startTime)
                                    || (currentLecture.getEndTime() > startTime
                                    && currentLecture.getEndTime() <= endTime)) {
                                return true;
                            }
                        }

                    }
                    else if (!newLecture.getTeacher().isInLab()) {

                        String labGroup1 = "3S", labGroup2 = "4S";
                        //TODO 2
                        String sub=newLecture.getGroup().substring(0,2);

                        if (sub.equalsIgnoreCase("G1")) {
                            labGroup1 = "1S";
                            labGroup2 = "2S";

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
                /** two practical teacher or group*/
                if (currentLecture.isPractical() == newLecture.isPractical() && newLecture.isPractical()
                        && (currentLecture.getTeacher().getName().equalsIgnoreCase(newLecture.getTeacher().getName())
                        || currentLecture.getGroup().equalsIgnoreCase(newLecture.getGroup()))) {

                    if ((currentLecture.getStartTime() < endTime
                            && currentLecture.getStartTime() >= startTime)
                            || (currentLecture.getEndTime() > startTime
                            && currentLecture.getEndTime() <= endTime)) {

                        return true;
                    }
                }
                /** two theoretical and same group*/
                if (currentLecture.isPractical() == newLecture.isPractical() && !newLecture.isPractical()
                        &&currentLecture.getGroup().equalsIgnoreCase(newLecture.getGroup())) {
                    if ((currentLecture.getStartTime() < endTime
                            && currentLecture.getStartTime() >= startTime)
                            || (currentLecture.getEndTime() > startTime
                            && currentLecture.getEndTime() <= endTime)) {
                        return true;
                    }
                }
                if (currentLecture.isPractical() != newLecture.isPractical()) {
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
                if (period.getStartTime() <= (float) (8 + (currentPosition) * 1.0)
                        && period.getEndTime() >= (10 + (currentPosition)) * 1.0) {
                    return true;
                }
            }
        }
        return false;
    }

    private Stage getNextStage(Schedule schedule, Lecture mLecture, int currentDay, int currentPosition, boolean isPractical) {
        int startTime = 8 + (currentPosition);
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
