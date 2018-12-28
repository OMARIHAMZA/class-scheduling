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
            ArrayList<Professor> professors = new ArrayList<>();
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
                professors.add(new Professor(currentLecturer.getString("name"), periods));
            }

            ArrayList<Teacher> teachers = new ArrayList<>();
            JSONArray teachersJSONArray = jsonObject.getJSONArray("teachers");
            for (int i = 0; i < teachersJSONArray.length(); i++) {
                JSONObject currentTeacher = teachersJSONArray.getJSONObject(i);
                JSONArray periodsJSONArray = currentTeacher.getJSONArray("periods");
                ArrayList<Period> periods = new ArrayList<>();
                for (int j = 0; j < periodsJSONArray.length(); j++) {
                    JSONObject currentPeriod = periodsJSONArray.getJSONObject(j);
                    periods.add(new Period(currentPeriod.getString("day_name"),
                            ((float) currentPeriod.getInt("start_time")),
                            ((float) currentPeriod.getInt("end_time"))));
                }
                ArrayList<String> groups = new ArrayList<>();
                JSONArray groupsJSONArray = currentTeacher.getJSONArray("groups");
                for (int j = 0; j < groupsJSONArray.length(); j++) {
                    groups.add(groupsJSONArray.getString(j));
                }
                teachers.add(new Teacher(currentTeacher.getString("name"),
                        currentTeacher.getString("course_name"),
                        currentTeacher.getBoolean("in_lab"),
                        periods,
                        groups
                ));
            }

            ArrayList<Lecture> lectures = new ArrayList<>();
            JSONArray lecturesJSONArray = jsonObject.getJSONArray("courses");
            for (int i = 0; i < lecturesJSONArray.length(); i++) {
                JSONObject currentLecture = lecturesJSONArray.getJSONObject(i);
                Professor currentProfessor = null;
                String currentLecturerName = currentLecture.getString("lecturer_name");
                for (Professor professor : professors) {
                    if (professor.getName().equalsIgnoreCase(currentLecturerName)) {
                        currentProfessor = professor;
                        break;
                    }
                }
                if (currentProfessor == null) {
                    throw new Exception("Professor: " + currentLecturerName + " not found! ");
                }
                lectures.add(new Lecture(currentLecture.getString("course_name"), currentProfessor));
            }

            ArrayList<Lecture> practicalLectures = new ArrayList<>();
            JSONArray practicalLecturesJSONArray = jsonObject.getJSONArray("practical_courses");
            for (int i = 0; i < practicalLecturesJSONArray.length(); i++) {
                JSONObject currentLecture = practicalLecturesJSONArray.getJSONObject(i);
                for (Teacher teacher : teachers) {
                    for (String group : teacher.getGroups()) {
                        if (teacher.getCourseName().equalsIgnoreCase(currentLecture.getString("name"))) {
                            practicalLectures.add(new Lecture(currentLecture.getString("name"), group, teacher));
                        }
                    }
                }
            }

            ArrayList<Stage> stages = new ArrayList<>();
            JSONArray stagesJSONArray = jsonObject.getJSONArray("stages");
            for (int i = 0; i < stagesJSONArray.length(); i++) {
                stages.add(new Stage(stagesJSONArray.getJSONObject(i).getString("name"), false));
            }


//            lectures.addAll(practicalLectures);

            return new Resources(lectures, professors, stages);


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return null;

    }

}
