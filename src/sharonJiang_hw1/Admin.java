package sharonJiang_hw1;
import java.util.ArrayList; 

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;


public class Admin extends User implements AdminInterface, Serializable{
	
	
	//constructor with only username and password
	public Admin(String u, String p, String fn, String ln) {
		super(u,p,fn,ln);
	}
	
	//just using name to create a class?
	//meaning by just using the name of class, 
	//then add the corresponding course object to the list of current courses?
	
	public void addStudentToClass(Course c,Student s, Data da) {
		ArrayList<Course>courses=da.getCourses();
		ArrayList<Student>students=da.getStudents();
		for (Course co:courses) {
			if (co.equals(c)) {
				for (Student stu:students) {
					if (stu.equals(s)) {
						String fullname=s.getFN()+" "+s.getLN();//create full name variable
						co.addStudent(fullname);//name is added to the list, current student number increment
					}
				}
			}
		}
		System.out.println("Student added successfully.");
		
	}
	
	public void removeStudentFromClass(Course c,Student s, Data da) {
		ArrayList<Course>courses=da.getCourses();
		ArrayList<Student>students=da.getStudents();
		for (Course co:courses) {
			if (co.equals(c)) {
				for (Student stu:students) {
					if (stu.equals(s)) {
						String fullname=s.getFN()+" "+s.getLN();
						co.removeStudent(fullname); //name is removed, current number -1
					}
				}
			}
		}
		System.out.println("Student removed successfully.");
		
	}
	
	@Override
	//create student, only admin can create a student
	public void registerStu(Scanner scnr, Data da) {
		System.out.print("To add register a new student, please enter the student's username first: ");
		String userName=scnr.nextLine().trim();
		System.out.print("Enter the password: ");
		String password=scnr.nextLine().trim();
		System.out.print("Enter student's first name: ");
		String fn=scnr.nextLine().trim();
		System.out.print("Enter student's last name: ");
		String ln=scnr.nextLine().trim();
		System.out.print("Enter student's id: ");
		String id=scnr.nextLine().trim();
		
		//when register a student, all the info is needed including password
		Student student=new Student(userName,password,fn,ln, id);
		da.addStudent(student);
		
		//not sure if a admin can add/delete a student from a course
		boolean regisClass=false;
		System.out.print("Student Created Successfully. If you want to add or remove a student from a course, enter 'next', else enter'exit': ");
		String action=scnr.nextLine().toLowerCase().trim();
		if (action.equals("next")) {
			regisClass=true;
			System.out.print("Please enter the course ID: ");
			 String courseID = scnr.nextLine().trim().toUpperCase();
	        Course ac = null;
	        for (Course co : da.getCourses()) {
	            if (co.getCourseID().equalsIgnoreCase(courseID)) {
	                ac = co;
	                break;
	            }
	        }

	        if (ac == null) {
	            System.out.println("Course not found.");
	            return;
	        }

	        // ask for add or remove
	        System.out.print("Type 'add' to add student to course or 'remove' to remove student: ");
	        String answer = scnr.nextLine().trim().toLowerCase();

	        switch (answer) {
	            case "add":
	                addStudentToClass(ac, student, da);
	                break;
	            case "remove":
	                removeStudentFromClass(ac, student, da);
	                break;
	            default:
	                System.out.println("Invalid choice.");
	        }

	    } else {
	        System.out.println("Exit Student Registration Page.");
	    }
	}

	@Override
	public void createCourse(Scanner scnr,Data da) {
		System.out.print("To add a new course, please enter the course name: ")	;
		String newCourse=scnr.nextLine().trim();
		System.out.print("Enter the course ID: ");
		String newID=scnr.nextLine().trim();
		System.out.print("Enter the capacity of the course: ");
		//capacity validation
		int newCap=scnr.nextInt();
		scnr.nextLine();
		
		//instantiate a new blank arraylist for student name storage
		ArrayList<String>students=new ArrayList<>();
		
		System.out.print("Please enter the name of the instructor: ");
		String instructor=scnr.nextLine().trim();
		System.out.print("Please enter the course section number: ");
		int section=scnr.nextInt();
		scnr.nextLine();
		System.out.print("Please enter the course location: ");
		String location=scnr.nextLine().trim();
		Course c=new Course (newCourse,newID, newCap, 0, students, instructor, section, location);
		System.out.println("Course created successfully.");
		da.addCourse(c);
		
			
	}
	
	@Override
	//delete a course with id
	public void deleteCourse(Scanner scnr, Data da) {
		System.out.print("Please the ID of the course you would like to delete: ");
		String id=scnr.nextLine().trim();
		ArrayList<Course>courses=da.getCourses();
		boolean found=false;
		for (int i = 0; i < courses.size(); i++) {
			Course c = courses.get(i);
	        if (c.getCourseID().equalsIgnoreCase(id)) {
	        	courses.remove(i); 
	        	System.out.println("Course " + id + " has been deleted.");
	            found = true;
	            break;
	        }
	    }

	    if (!found) {
	        System.out.println("Sorry, invalid ID. No course deleted.");
	    }
		
	}
	
	@Override
	//edit a course
	public void editCourse(Scanner scnr, Data da) {
		String id;
		System.out.print("Please enter the ID of the Course you want to edit: ");
		id=scnr.nextLine().toUpperCase().trim(); //the course id is all caps
		ArrayList<Course>courses=da.getCourses();
		Course edit=null;
		for (Course c: courses) {
			if (id.equals(c.getCourseID())) {
				edit=c;
				break;
			}
		}
		if (edit==null) {
			System.out.println("Course not found.")	;
			return;
		}
		
		boolean editing=true;
		while(editing) {
				System.out.println("Please choose which editing task to perform");
				System.out.println("1.Capacity");
				System.out.println("2.Number of Current Enrolled Students");
				System.out.println("3.List of Current Enrolled Students");
				System.out.println("4.Instuctor");
				System.out.println("5.Section Number");
				System.out.println("6.Location");
				System.out.print("7.Exit editing ");
				String choice=scnr.nextLine().trim();
				switch (choice) {
					case ("1"):
						System.out.print("The capacity of Course " +id+" is "+edit.getCapacity()+" .Enter the new capacity here: ");
						int newCap=scnr.nextInt();
						scnr.nextLine();
						edit.setCapacity(newCap);
						break;
					case ("2"):
						System.out.print("The number of current enroll student of Course "+id+" is "+edit.getCurrentNum()+" .Enter the new number of enrolled student here: ");
						int newStu=scnr.nextInt();
						scnr.nextLine();
						edit.setCurrentNum(newStu);
						break;
					case ("3"): 
						//enter new names, set a new ArrayList
						ArrayList<String>upload=new ArrayList<>();
						System.out.println("Enter the student name one by one, type 'stop' when done: ");
						while (true) {
							String input = scnr.nextLine().trim();
					        if (input.equalsIgnoreCase("stop")) {
					          break; // exit loop
					        }
					        upload.add(input); // add input to ArrayList
					    }
						edit.setStuNames(upload);
						break;
						
					case ("4"):
						System.out.print("The instructor of Course " +id+" is "+edit.getInstructor()+". Enter the new instructor here: ");
						String newProf=scnr.nextLine();
						edit.setIntr(newProf);
						break;
					case ("5"):
						System.out.print("The section number of Course "+id+" is "+edit.getSection());
					   	int newSection;
					    while (true) {
					        System.out.print("Enter new section number (integer only): ");
					        String input = scnr.nextLine().trim();
					        try {
					            newSection = Integer.parseInt(input);
					            break; 
					        } catch (NumberFormatException e) {
					            System.out.println("Invalid input! Please enter a number.");
					        }
					    }
					    edit.setSection(newSection);
					    break;
					case ("6"):
						System.out.print("The location of Course " +id+" is "+edit.getLocation()+". Enter the new location here: ");
						String newLoc=scnr.nextLine();
						edit.setIntr(newLoc);
						break;
					case ("7"):
						editing=false;
						System.out.println("Editing is done.");
					default: 
						System.out.println("Invalid choice, try again.");
				
				}

			}

		}
	
	@Override
	//display the course info given its id
	public void displayCourse(Scanner scnr, Data da) {
		String i=null;
		System.out.print("Please enter the ID of the course you want to display: ");
		i=scnr.nextLine();
		ArrayList<Course>courses=da.getCourses(); 
		for (Course c:courses) {
			if (c.getCourseID().equals(i)) {
				System.out.println("Course: "+c.getCourseName());
				System.out.println("ID: "+c.getCourseID());
				System.out.println("Capacity: "+c.getCapacity());
				System.out.println("Current Number of Registered Students: "+c.getCurrentNum());
				System.out.println("List of enrolled student: "+c.getStudents());
				System.out.println("Instructor: "+c.getInstructor());
				System.out.println("Section: "+c.getSection());
				System.out.println("Location: "+c.getLocation());
				break;
				
			}
		}
	}
	//courses management is done
	
	@Override
	//View all courses
	public void viewAll(Data da) {
		for (Course course: da.getCourses()) {
			System.out.println("Name: "+course.getCourseName());
			System.out.println("Course ID: "+course.getCourseID());
			System.out.println("Capacity: "+course.getCapacity());
			System.out.println();
			
		}
	}
	
	@Override
	//view all full courses by names
	public void viewFull(Data da) {
		System.out.println("Here is the list of full courses: ");
		for (Course c:da.getCourses()) {
			if (c.isfull()) {
				System.out.println(c.getCourseName());
			}
		}
	}
	
	@Override
	//write to a file the list of full courses
	public void writeFull(Data da) {
	    String folder = "data"; // folder to store the file
	    String fileName = "fullCourses.txt";
	    
	    // Create the folder if it doesn't exist
	    File dir = new File(folder);
	    if (!dir.exists()) {
	        dir.mkdir();
	    }
	    
	    File file = new File(dir, fileName); // file path: data/fullCourses.txt
	    
	    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
	        for (Course c : da.getCourses()) {
	            if (c.isfull()) {
	                bufferedWriter.write(c.getCourseName());
	                bufferedWriter.newLine();
	            }
	        }
	        System.out.println("Full courses written successfully to " + file.getAbsolutePath());
	    } catch (IOException ex) {
	        System.out.println("Error writing file '" + file.getAbsolutePath() + "'");
	        ex.printStackTrace();
	    }
	}
	
	@Override
	//view the names of registered student of a course
	public void viewNames(Scanner scnr, Data da) {
		System.out.print("To view the names of students being registered in a course, please enter the name of the course: ");
		String cn=scnr.nextLine().trim();
		for (Course c:da.getCourses()) {
			if (cn.equalsIgnoreCase(c.getCourseName())){
				System.out.println(c.getStudents());
			}
		}
	}
	
	@Override
	//View the list of Courses given a student's first and last name
	public void viewCourse(Scanner scnr, Data da) {
		System.out.println("To view the list of courses a student is being registered on, please enter the student's first name and last name:" );
		System.out.print("Student's first name: ");
		String fn=scnr.nextLine().trim();
		System.out.print("Student's last name: ");
		String ln=scnr.nextLine().trim();
		Student s=null;
		for (Student st: da.getStudents()) {
			if (st.getFN().equalsIgnoreCase(fn)&&st.getLN().equalsIgnoreCase(ln)) {
				s=st;
				break;
			}
		}
		ArrayList<Course>enrolled=s.getEnroll();
		System.out.println("Here are the courses student " + s.getFN()+" "+s.getLN()+" currently register on:");
		for (Course c: enrolled) {
			System.out.println(c);
		}
		
	}
	

}
