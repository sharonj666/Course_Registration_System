package sharonJiang_hw1;
import java.util.List;
import java.io.*;
import java.util.ArrayList;


//should I make the data getter static 
public class Data implements Serializable {
	private ArrayList<Course>courses=new ArrayList<Course>();
	private ArrayList<Student>students=new ArrayList<Student>();
	
	public Data() {
		//non-args constructor
	}
	public Data(ArrayList<Course> c, ArrayList<Student> s) {
		this.courses = c;
		this.students = s;
	}
	
	//getters and setters
	public void setCourses(ArrayList<Course> c) {
		this.courses=c;	}
	
	public void setStudents(ArrayList<Student> s) {
		this.students=s;
	}
	
	public ArrayList<Course> getCourses(){
		return courses;
	}
	
	public ArrayList<Student> getStudents(){
		return students;
	}
	
	//adding methods
	 public void addCourse(Course c) { courses.add(c); }
	 public void addStudent(Student s) { students.add(s); }
	 
	

	
	

}
