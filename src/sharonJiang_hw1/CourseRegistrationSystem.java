package sharonJiang_hw1;
import java.util.*;
import java.io.*;

public class CourseRegistrationSystem {
	
	
	
	
	public static void main (String[] args) throws IOException {
		//deserialize the program if not the first time log in
		Scanner scnr= new Scanner(System.in);
		Data da=null;
		try (FileInputStream fis = new FileInputStream("Data.ser");
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			da= (Data) ois.readObject();
		    System.out.println("Data deserialized successfully!");
		}
		catch(Exception e) {
			//read the csv file
			da=new Data();
			String path="data/MyUniversityCourses.csv";
			List<Course>loaded=CourseReader.loadCourses(path);
			da.getCourses().addAll(loaded);
			System.out.println("Courses loaded.");

		}
			
		//Ask the admin to login first (required)
		boolean login=false;
		Admin admin=null;
		while (!login) {
			System.out.println("If you are an Admin, please log in with your username and password.");
	        System.out.print("Username: ");
	        String username = scnr.nextLine().trim();
	        System.out.print("Password: ");
	        String password = scnr.nextLine().trim();
	        System.out.print("First name: ");
	        String fn=scnr.nextLine().trim();
	        System.out.print("Last name: ");
	        String ln=scnr.nextLine().trim();
	        if (username.equals("Admin") && password.equals("Admin001")) {
	            admin = new Admin(username, password, fn, ln);
	            login = true;
	            System.out.println("Admin logged in successfully!");
	        } else {
	            System.out.println("Invalid admin credentials, try again.");
	        }
			
		}
		
		//admin has been created
		//loop through the user menu sessions
		boolean quit=false;
		Student loggedInStudent=null;
		while (!quit) {
	        System.out.println("1. Admin login");
	        System.out.println("2. Student login");
	        System.out.println("3. Exit program");
	        System.out.print("Enter choice: ");
	        String user=scnr.nextLine().trim();
	        
	        //switch case to call the corresponding role menu
	        switch(user) {
	        	case "1": //admin log in
	        		System.out.print("Enter username: ");
	        		String un=scnr.nextLine().trim();
	        		System.out.print("Enter password: ");
	        		String pw=scnr.nextLine().trim();
	        		if (un.equals("Admin")&&pw.equals("Admin001")) {
	        			AdminMenu.logIn(admin,  scnr, da);
	        		}
	        		else {
	        			System.out.println("Invalid admin credentials, try again.");
	        		}
	        		break;
	        	case "2":
	        		System.out.print("Enter username: ");
	        		String studentUN=scnr.nextLine().trim();
	        		System.out.print("Enter the password: ");
	        		String studentPW=scnr.nextLine().trim();
	        		System.out.print("Enter student's first name: ");
	        		String fn=scnr.nextLine().trim();
	        		System.out.print("Enter student's last name: ");
	        		String ln=scnr.nextLine().trim();
	        		System.out.print("Enter student's id: ");
	        		String id=scnr.nextLine().trim();
	        
	        		//check if the student is registered
	        		for (Student s: da.getStudents()) {
	        			if (s.getFN().equalsIgnoreCase(fn)&&s.getLN().equalsIgnoreCase(ln)){
	        				loggedInStudent=s;
	        				break;
	        			}
	        		}
	        		
	        		//if a student is registered, continue the student menu
	        		if (loggedInStudent!=null) {
	        			StudentMenu.studentLogIn(loggedInStudent, scnr, da);
	        		}else {
	        			System.out.println("Student not found. Please ask the admin to register you");
	        		}
	        	case "3":
	        		quit=true;
	        		break;
	        	default:
	        		System.out.println("Invalid choice.");
	        		
	        }
	        
		}
		
		
		//serialize the data
		
		try (FileOutputStream fos = new FileOutputStream("Data.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
		        oos.writeObject(da);
		        System.out.println("Data saved successfully. Goodbye!");
		    }
		
	}
}
