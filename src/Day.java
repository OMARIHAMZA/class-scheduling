import java.util.ArrayList;

public class Day {
    int num;
    ArrayList<Lectuer>lectuers;

    public Day(int num, ArrayList<Lectuer> lectuers) {
        this.num = num;
        this.lectuers = lectuers;
    }

    public Day(int num) {
        this.num = num;
    }
}
