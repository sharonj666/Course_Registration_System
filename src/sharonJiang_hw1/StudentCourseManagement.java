package sharonJiang_hw1;
import java.util.Scanner;

public interface StudentCourseManagement {
	public void viewAll(Data da);
	public void viewNotFull(Data da);
	public void register(Scanner cn, Data da);
	public void withdraw(Scanner cn, Data da);
	public void viewRegister();
}
