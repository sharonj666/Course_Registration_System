package sharonJiang_hw1;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Student extends User implements StudentCourseManagement, Serializable{
	
	//first name, last name, password, username
	private String studentID;
	private ArrayList<Course>enroll;
	
	//setter
	public void setID(String i) {
		//set the student id length to 5 
		if (i.length()>5) {
			studentID=i;
		}else {
			System.out.println("Invalid id");
		}
	}
	
	//getters
	public String getFN() {
		return this.fn;
	}
	
	public String getLN() {
		return this.ln;
	}
	
	
	
	public ArrayList<Course> getEnroll(){
		return enroll;
	}
	
	public String getStudentID() {
		return studentID;
	}
	
	
	//constructor	
	public Student(String u, String p, String f, String l, String id) {
        super(u, p, f, l);// calls User constructor
        setID(id);
        this.enroll = new ArrayList<>();
    }
	
	public Student() {
		super();
		this.enroll=new ArrayList<>();
		
	}
	
	//an add course method
	public void addCourse(Course c) {
	    enroll.add(c);
	}
	
	//an remove course method
	public void removeCourse(Course c) {
		enroll.remove(c);
	}
	
	//implement Student course management methods
	
	@Override
	//view all the courses
	public void viewAll(Data da) {
		for (Course course: da.getCourses()) {
			System.out.println("Name: "+course.getCourseName());
			System.out.println("Course ID: "+course.getCourseID());
			System.out.println("Capacity: "+course.getCapacity());
			System.out.println();
			
		}
	}
	
	@Override
	//view courses that are not full
	public void viewNotFull(Data da) {
		System.out.println("Here is the list of courses that are not full: ");
		for (Course c:da.getCourses()) {
			if (!c.isfull()) {
				System.out.println(c.getCourseName());
			}
		}
	}
	
	public void register(Scanner scnr, Data da) {
		String cn;
		int sec;
		System.out.println("To register on a course, please enter the course name, section, and your full name");
		System.out.print("Course name: ");
		cn=scnr.nextLine().trim();
		System.out.print("Course section: ");
		sec=scnr.nextInt();
		scnr.nextLine();
		System.out.print("Your first name: ");
		String fn=scnr.nextLine();
		System.out.print("Your last name: ");
		String ln=scnr.nextLine();
		
		//find the student
		Student s=null;
		for (Student st: da.getStudents()) {
			if (st.getFN().equalsIgnoreCase(fn)&&st.getLN().equalsIgnoreCase(ln)) {
				s=st;
				break;
			}
		}
		if (s == null) {
		    System.out.println("Student not found!");
		    return;
		}
		for (Course c:da.getCourses()) {
			if (c.getCourseName().equalsIgnoreCase(cn)&&c.getSection()==sec&&!enroll.contains(c)) {
				String fullname=s.getFN()+" "+s.getLN();
				c.getStudents().add(fullname); //student's name added to the corresponding course
				s.addCourse(c); //corresponding course added to student's list of enroll
				System.out.println("Course registered successfully!");
				return;
			}
		}
		System.out.println("Course not found or check if you already registered on this course.");
		
	}
	public void withdraw(Scanner scnr, Data da) {
		System.out.println("To withdraw from a course, please enter your full name and the course name");
		System.out.print("Your first name: ");
		String fn=scnr.nextLine();
		System.out.print("Your last name: ");
		String ln=scnr.nextLine();
		System.out.print("Course name: ");
		String cn=scnr.nextLine().trim();
		//find the student
		Student s=null;
		for (Student st: da.getStudents()) {
			if (st.getFN().equalsIgnoreCase(fn)&&st.getLN().equalsIgnoreCase(ln)) {
				s=st;
				break;
			}
		}
		String fullname=s.getFN()+" "+s.getLN();
		if (s == null) {
			System.out.println("Student not found!");
			return;
		}
		
		for (Course c: da.getCourses()) {
			if (c.getCourseName().equalsIgnoreCase(cn)&&c.getStudents().contains(fullname)&&s.enroll.contains(cn)) {
				c.getStudents().remove(fullname); //remove the student from the corresponding course's student name list
				s.removeCourse(c); //remove the corresponding course from the student's list of enroll 
				System.out.println("Course withdraw successfully!");
				return;
			}
		}
		System.out.println("Course not found or check if you already dropped this course.");

	}
	
	@Override
	//view the course the student registered on
	public void viewRegister() {
	    System.out.println("Here are the courses you are currently registered on:");
	    if (enroll.isEmpty()) {
	        System.out.println("You are not registered in any course.");
	    } else {
	    	for (Course c: enroll) { 
	    		System.out.println(c); 
	    	}
	    }
	}
}
