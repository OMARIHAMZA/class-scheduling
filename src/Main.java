import models.Day;
import models.Lecture;
import models.Schedule;
import utils.Utils;

public class Main {

    public static void main(String[] args) {
        Schedule schedule = new Schedule(Utils.readDataFromFile("input.json"));
        schedule = schedule.generateSchedule();
        for (Day day : schedule.getDays()) {
            System.out.println("-------------");
            System.out.println(day.getDayName());
            for (Lecture lecture : day.getLectures()) {
                if (lecture.isPractical()) {
                    System.out.print(lecture.getCourseName() + " _ " + lecture.getTeacher().getName() + "\n");
                } else {
                    System.out.print(lecture.getCourseName() + " * " + lecture.getProfessor().getName() + "\n");
                }
            }
            System.out.println();
        }
    }

}
