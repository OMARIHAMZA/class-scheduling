package utils;

import models.Lecture;
import models.Lecturer;
import models.Period;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static void readDataFromFile(String filePath) {
        try {

            JSONObject jsonObject = new JSONObject(new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next());
            ArrayList<Lecturer> lecturers = new ArrayList<>();
            JSONArray lecturerJSONArray = jsonObject.getJSONArray("lecturers");
            for (int i = 0; i < lecturerJSONArray.length(); i++) {
                JSONObject currentLecturer = lecturerJSONArray.getJSONObject(i);
                JSONArray periodsJSONArray = currentLecturer.getJSONArray("periods");
                ArrayList<Period> periods = new ArrayList<>();
                for (int j = 0; j < periodsJSONArray.length(); j++) {
                    JSONObject currentPeriod = periodsJSONArray.getJSONObject(j);
                    periods.add(new Period(currentPeriod.getString("day_name"),
                            currentPeriod.getString("start_time"),
                            currentPeriod.getString("end_time")));
                }
                lecturers.add(new Lecturer(currentLecturer.getString("name"), periods));
            }

            ArrayList<Lecture> lectures = new ArrayList<>();
            JSONArray lecturesJSONArray = jsonObject.getJSONArray("courses");
            for (int i = 0; i < lecturesJSONArray.length(); i++) {
                JSONObject currentLecture = lecturesJSONArray.getJSONObject(i);
                Lecturer currentLecturer = null;
                String currentLecturerName = currentLecture.getString("lecturer_name");
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getName().equalsIgnoreCase(currentLecturerName)) {
                        currentLecturer = lecturer;
                        break;
                    }
                }
                if (currentLecturer == null) {
                    throw new Exception("Lecturer: " + currentLecturerName + " not found! ");
                }
                lectures.add(new Lecture(currentLecture.getString("course_name"), currentLecturer));
            }



        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

}
