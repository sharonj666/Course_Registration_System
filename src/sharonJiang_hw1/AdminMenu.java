package sharonJiang_hw1;
import java.util.Scanner;

public class AdminMenu {

	public static void logIn(Admin admin, Scanner scnr, Data da) {
		boolean exit=false;
		while (!exit) {
			System.out.println("--- Admin Menu ---");
			System.out.println("1.Create Course");
			System.out.println("2.Delete Course");
			System.out.println("3.Edit a Course");
			System.out.println("4.Diaplay a Course Information");
			System.out.println("5.Register a Student"); //create a student/assign a student to a course
			System.out.println("6.View all Courses");
			System.out.println("7.Write a full course to the file"); //not sure
			System.out.println("8.Enter a class, View the names of students being registered in it");
			System.out.println("9.Enter a student, View the student's registered courses");
			System.out.println("Type 'exit' to log out");		
			System.out.print("Please enter the task number: ");
			String choice=scnr.nextLine().toLowerCase().trim();
			
			//use switch case to call corresponding method
			switch(choice){
				case "1":
					admin.createCourse(scnr, da);
					break; 
				case "2":
					admin.deleteCourse(scnr, da);
					break;
				case "3":
					admin.editCourse(scnr, da);
					break;
				case "4":
					admin.displayCourse(scnr, da);
					break;
				case "5":
					admin.registerStu(scnr, da);
					break;
				case "6":
					admin.viewAll(da);
					break;
				case "7":
					admin.writeFull(da);
					break;
				case "8":
					admin.viewNames(scnr, da);
					break;
				case "9":
					admin.viewCourse(scnr, da);
					break;
				case "exit":
					exit=true;
				
				default:
					System.out.println("Invalid choice. Try again.");
			}
		}
	}
}
