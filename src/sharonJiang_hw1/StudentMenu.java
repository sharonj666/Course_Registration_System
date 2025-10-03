package sharonJiang_hw1;
import java.util.Scanner;

public class StudentMenu {
	
	public static void studentLogIn(Student loggedInStudent, Scanner scnr, Data da) {
		boolean exit=false;
		while (!exit) {
			System.out.println("--- Student Menu ---");
            System.out.println("1. View all Courses");
            System.out.println("2. View available (non-full) Courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. Withdraw from a Course");
            System.out.println("5. View my registered Courses");
            System.out.println("Type 'exit' to log out.");

            String choice = scnr.nextLine().toLowerCase().trim();
            
            switch (choice) {
            	case "1": loggedInStudent.viewAll(da); break;
            	case "2": loggedInStudent.viewNotFull(da); break;
            	case "3": loggedInStudent.register(scnr, da); break;
            	case "4": loggedInStudent.withdraw(scnr, da); break;
            	case "5": loggedInStudent.viewRegister(); break;
            	case "exit": exit = true; break;
            	default: System.out.println("Invalid choice.");
            }
		}
 
	}

}
