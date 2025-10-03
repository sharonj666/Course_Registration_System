package sharonJiang_hw1;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

public class Course implements Serializable{
	public static List<Course>currentCourses;//!!!
	private String name;
	private String id;
	private int capacity;
	private int currentNum;
	private ArrayList<String>students;
	private String instructor;
	private int section;
	private String location;

	
	//getters
	public List<Course> getCourses(){return currentCourses;} //!!!
	public String getCourseName() { return name; }
	public String getCourseID() { return id; }
	public int getCapacity() { return capacity; }
	public int getCurrentNum() { return currentNum; }
	public ArrayList<String> getStudents() { return students; }
	public String getInstructor() { return instructor; }
	public int getSection() { return section; }
	public String getLocation() { return location; }
	
	//setters
	public void setName(String n) {
		this.name=n;
	}
	
	public void setID(String i) {
		this.id=i;
	}

	public void setCapacity(int n) {
		if (n>0&&n<100) {
			this.capacity=n;
		}else {
			capacity=30; //set the default capacity to 30
		}
	}
		
	public void setCurrentNum(int cn) {
		this.currentNum=cn;
	}
	
	public void setStuNames(ArrayList<String>names) {
		this.students=names;
	}
	
	public void setIntr(String pro) {
		this.instructor=pro;
	}
	
	public void setSection(int sec) {
		this.section=sec;
	}
	
	public void setLoc(String loc) {
		this.location=loc;
	}
	
	//if a student is added to the course
	//a name is added to the name list 
	//current number of students increment 
	 public void addStudent(String name) {
		 if (students == null) { 
			 students = new ArrayList<>();
	     }
		 students.add(name);
	     currentNum++;
	 }
	
	//student is remove
	public void removeStudent(String sn) {
		this.students.remove(sn);
		this.currentNum--;
	}
	
	//boolean to check for full
	public boolean isfull() {
		boolean isFull=false;
		if (this.capacity==this.currentNum) {
			return true;
		}
		return false;
	}
	
	@Override
	//for student to view their registered courses
	//student can view the course name, id, instructor, section, location
	public String toString() {
	    return "Course Name: " + name +", Course ID: " + id+ ", Instructor: " + instructor+", Section: " + section+", Location: " +location;
	}
		
	//no args constructor
	public Course() {
	}
	//contructor
	public Course(String name, String id, int capacity , int current, ArrayList<String>students, String instructor, int section, String location ) {
		this.name=name;
		this.id=id;
		this.capacity=capacity;
		this.currentNum=current;
        if (students != null) {
            this.students = students;
        } else {
            this.students = new ArrayList<>();
        }
		this.instructor=instructor;
		this.section=section;
		this.location=location;
		
		
	}
	
	public static void main (String[] args) {
		String filePath="data/MyUniversityCourses.csv";
		List<Course>courses=CourseReader.loadCourses(filePath);
		
		
		//test code for getting the right data
		/*
		for (Course c: courses) {
			System.out.println(c.getCourseName());
			System.out.println(c.getCourseID());
		}
		*/
	
	}
}
