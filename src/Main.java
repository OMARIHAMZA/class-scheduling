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
                System.out.print(lecture.getCourseName() + " * " + lecture.getLecturer().getName() + "\n");
            }
            System.out.println();
        }
    }

}
