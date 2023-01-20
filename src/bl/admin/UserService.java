package bl.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import core.entity.User;
import core.entity.UserDetails;
import core.enums.Role;

public class UserService {

	static List<User> userList = new ArrayList<>(Arrays
			.asList(new User(new UserDetails("Admin", "User", "admin@soen.com", "admin"), Role.ADMIN)));

	public static List<User> getUserList() {
		return userList;
	}

	public static void putUserList(User user) {
		userList.add(user);
	}

	public static void login() {
		while (true) {
			System.out.println("Please login with your registered credentials");

			Scanner scanner = new Scanner(System.in); // java.io.console does not work on Eclipse

			System.out.println("Email Id: ");
			String email = scanner.nextLine();
			System.out.println("Password: ");
			String password = scanner.nextLine();

//		scanner.close();

			User loggedInUser = getUserList().stream()
					.filter(user -> user.getUserDetails().getEmail().equals(email)
							&& user.getUserDetails().getPassword().equals(password))
					.findFirst().orElse(null);

			if (loggedInUser == null) {
				System.out.println("Sorry, you have entered wrong credentials!");
				continue;
			}

			System.out.println("Welcome " + loggedInUser.getUserDetails().getFirstName());

			if (loggedInUser.getRole().equals(Role.ADMIN)) {
				AdminService adminService = new AdminService();
				adminService.menu();
			} else if (loggedInUser.getRole().equals(Role.OWNER)) {
				OwnerService ownerService = new OwnerService();
				ownerService.menu();
			} else if (loggedInUser.getRole().equals(Role.CLIENT)) {
				ClientService clientService = new ClientService();
				clientService.menu();
			} else {
				System.out.println("ERROR");
			}
		}
	}

	public static void logout() {
		System.out.println("You have been successfully logged out");
		login();
	}

}
