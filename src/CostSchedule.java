import java.util.ArrayList;

public class CostSchedule {


    public boolean basicConditions(Schedule schedule,Lectuer lectuer){
        for (Day days :schedule.days) {
            for (Lectuer l:days.lectuers) {
                // the same place and time
                // the same subject
                if (l.isequal(lectuer))return false;
            }
        }
        return true;
    }


}

