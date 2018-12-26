package models;

public class Period {

    private String dayName;
    private String startTime, endTime;

    public Period(String dayName, String startTime, String endTime) {
        this.dayName = dayName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
