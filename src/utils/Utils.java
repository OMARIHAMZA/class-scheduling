package utils;

import models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    public static Resources readDataFromFile(String filePath) {
        try {

            JSONObject jsonObject = new JSONObject(new Scanner(new FileInputStream(filePath)).useDelimiter("\\Z").next());
            ArrayList<Lecturer> lecturers = new ArrayList<>();
            JSONArray lecturerJSONArray = jsonObject.getJSONArray("professors");
            for (int i = 0; i < lecturerJSONArray.length(); i++) {
                JSONObject currentLecturer = lecturerJSONArray.getJSONObject(i);
                JSONArray periodsJSONArray = currentLecturer.getJSONArray("periods");
                ArrayList<Period> periods = new ArrayList<>();
                for (int j = 0; j < periodsJSONArray.length(); j++) {
                    JSONObject currentPeriod = periodsJSONArray.getJSONObject(j);
                    periods.add(new Period(currentPeriod.getString("day_name"),
                            ((float) currentPeriod.getInt("start_time")),
                            ((float) currentPeriod.getInt("end_time"))));
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

            ArrayList<Stage> stages = new ArrayList<>();
            JSONArray stagesJSONArray = jsonObject.getJSONArray("stages");
            for (int i = 0; i < stagesJSONArray.length(); i++) {
                stages.add(new Stage(stagesJSONArray.getJSONObject(i).getString("name"), false));
            }

            return new Resources(lectures, lecturers, stages);


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return null;

    }

}
