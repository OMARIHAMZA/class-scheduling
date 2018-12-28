import models.Day;
import models.Lecture;
import models.Schedule;
import utils.Utils;

public class Main {

    public static void main(String[] args) {
        Schedule schedule = new Schedule(Utils.readDataFromFile("input.json"));
        schedule = schedule.generateSchedule(schedule, 0, 0);
        for (Day day : schedule.getDays()) {
            System.out.println("-------------");
            System.out.println(day.getDayName());
            for (Lecture lecture : day.getLectures()) {
                if (lecture.isPractical()) {
                    System.out.println(lecture.getCourseName() + " _ " + lecture.getTeacher().getName() + "_" + lecture.getStartTime() + " -> " + lecture.getEndTime());
                } else {
                    System.out.println(lecture.getCourseName() + " * " + lecture.getProfessor().getName() + "_" + lecture.getStartTime() + " -> " + lecture.getEndTime());
                }
            }
            System.out.println();
        }
    }

}
