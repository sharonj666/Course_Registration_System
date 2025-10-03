package sharonJiang_hw1;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseReader implements Serializable{
	private ArrayList<Course>course;
    
    public static List<Course> loadCourses(String path) {
        ArrayList<Course> courses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) { // <-- fixed ) placement
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] values = line.split(",");
                String courseName = values[0].trim();
                String courseID = values[1].trim();
                int capacity = Integer.parseInt(values[2].trim());
                int currentNum = 0;
                ArrayList<String> students = new ArrayList<String>();
                String instructor = values[5].trim();
                int section = Integer.parseInt(values[6].trim());
                String location = values[7].trim();
                
                Course course = new Course(courseName, courseID, capacity, currentNum, students, instructor, section, location);
                courses.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
 
}

