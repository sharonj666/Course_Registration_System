package sharonJiang_hw1;
import java.util.Scanner;

public interface AdminInterface {
	
	//course management
	public void createCourse(Scanner scnr, Data da);
	
	public void deleteCourse(Scanner scnr, Data da);
	
	public void editCourse(Scanner scnr, Data da);
	
	public void displayCourse(Scanner scnr, Data da);
	
	public void registerStu(Scanner scnr, Data da);
	
	//reports
	public void viewAll(Data da);
	
	public void viewFull(Data da);
	
	public void writeFull(Data da);
	
	public void viewNames(Scanner scnr, Data da);
	
	public void viewCourse(Scanner scnr, Data da);
	
	
	

}
