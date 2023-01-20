package bl.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.entity.User;

public class UserService {

	static List<User> userList = new ArrayList<>();

	public static List<User> getUserList() {
		return userList;
	}

	public static void putUserList(User user) {
		userList.add(user);
	}

	public static String login() {
		System.out.println("Hi, please login with your registered credentials");

		Scanner scanner = new Scanner(System.in); // java.io.console does not work on Eclipse

		System.out.println("Email Id: ");
		String email = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();

//	scanner.close();

//TODO: Authenticate and display first name.
		System.out.println("Welcome " + email);
		return email;
	}

}
