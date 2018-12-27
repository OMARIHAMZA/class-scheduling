package models;

public class Period {

    private String dayName;
    private float startTime, endTime;

    public Period(String dayName, float startTime, float endTime) {
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

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }
}
