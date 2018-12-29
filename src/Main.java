import models.Day;
import models.Lecture;
import models.Schedule;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Schedule schedule = new Schedule(Utils.readDataFromFile("input.json"));
        long startTime = System.currentTimeMillis();
        schedule = schedule.generateSchedule(schedule, 0, 0);
        long endTime = System.currentTimeMillis();
        System.out.println("Time = "+(endTime-startTime));
        System.out.println("size = "+schedule.getSchedules().size());
        System.out.println("counter = "+schedule.counter);
        for (Day day : schedule.getDays()) {
            System.out.println("-------------");
            System.out.println(day.getDayName());
            for (Lecture lecture : day.getLectures()) {
                if (lecture.isPractical()) {
                    if (!lecture.getTeacher().isInLab()) {
                        System.out.println(lecture.getCourseName()
                                + " _ " + lecture.getTeacher().getName()
                                + "_" + lecture.getStartTime() + " -> " + lecture.getEndTime()
                                + " | stage: " + lecture.getStage().getName()
                                + " | Group: " + lecture.getGroup());
                    } else {
                        System.out.println(lecture.getCourseName()
                                + " _ " + lecture.getTeacher().getName()
                                + "_" + lecture.getStartTime() + " -> " + lecture.getEndTime()
                                + " | Lab: " + lecture.getStage().getName()
                                + " | LabGroup: " + lecture.getGroup());
                    }
                } else {
                    System.out.println(lecture.getCourseName() + " * " + lecture.getProfessor().getName() + "_" + lecture.getStartTime() + " -> " + lecture.getEndTime() + " | " + lecture.getStage().getName());
                }
            }
            System.out.println();
        }
    }

}
