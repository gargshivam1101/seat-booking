package bl.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import core.entity.User;
import core.entity.UserDetails;
import core.enums.Role;

public abstract class UserService implements IUserService {

	public abstract void menu();

	static List<User> userList = new ArrayList<>(Arrays.asList(//
			new User(new UserDetails("Admin", "User", "admin@soen.com", "admin"), Role.ADMIN), //
			new User(new UserDetails("Shivam", "Garg", "shivam.garg@soen.com", "garg"), Role.OWNER), //
			new User(new UserDetails("SH", "GA", "s.g@soen.com", "client"), Role.CLIENT), //
			new User(new UserDetails("Client", "Garg", "client.garg@soen.com", "client"), Role.CLIENT)));

	static User loggedInUser = null;

	public static List<User> getUserList() {
		return userList;
	}

	public static void putUserList(User user) {
		userList.add(user);
	}

	public static void login() {
		while (true) {
			System.out.println("✦•······················•✦•······················•✦");
			System.out.println("Please login with your registered credentials");

			Scanner scanner = new Scanner(System.in); // java.io.console does not work on Eclipse

			System.out.println("Email Id: ");
			String email = scanner.nextLine();
			System.out.println("Password: ");
			String password = scanner.nextLine();

//		scanner.close();

			User lgInUser = getUserList().stream()
					.filter(user -> user.getUserDetails().getEmail().equals(email)
							&& user.getUserDetails().getPassword().equals(password))
					.findFirst().orElse(null);

			if (lgInUser == null) {
				System.out.println("Sorry, you have entered wrong credentials!");
				continue;
			}

			loggedInUser = lgInUser;

			System.out.println("Welcome " + lgInUser.getUserDetails().getFirstName());

			if (lgInUser.getRole().equals(Role.ADMIN)) {
				AdminService adminService = new AdminService();
				adminService.menu();
			} else if (lgInUser.getRole().equals(Role.OWNER)) {
				OwnerService ownerService = new OwnerService();
				ownerService.menu();
			} else if (lgInUser.getRole().equals(Role.CLIENT)) {
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
