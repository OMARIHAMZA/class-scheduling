import models.Schedule;
import utils.Utils;

public class Main {

    public static void main(String[] args) {
        Schedule schedule = new Schedule(Utils.readDataFromFile("input.json"));
    }

}
