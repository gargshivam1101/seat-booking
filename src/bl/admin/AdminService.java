package bl.admin;

import java.util.Scanner;

import core.entity.User;
import core.entity.UserDetails;
import core.enums.Role;

public class AdminService extends UserService {

	public void menu() {
		System.out.println("Welcome Admin! Please choose an option from the below menu");

		System.out.println("1. Register a business owner");
		System.out.println("2. Register a client");
		System.out.println("3. Display all business owners");
		System.out.println("4. Display ");

		Scanner scanner = new Scanner(System.in);
		System.out.println("Press an option: ");
		Integer option = scanner.nextInt();
		scanner.nextLine();
		switch (option) {
		case 1:
			registerUser(Role.OWNER);
			System.out.println(getUserList());
			break;
		case 2:
			registerUser(Role.CLIENT);
			System.out.println(getUserList());
			break;
		default:
			System.out.println("Wrong option!");
			break;
		}
	}

	private void registerUser(Role role) {
		Scanner scanner = new Scanner(System.in);
		String firstName, lastName, email, password;
		System.out.println("Please enter details of the user");
		System.out.println("First Name: ");
		firstName = scanner.nextLine();
		System.out.println("Last Name: ");
		lastName = scanner.nextLine();
		System.out.println("Email: ");
		email = scanner.nextLine();
		System.out.println("Password: ");
		password = scanner.nextLine();
		UserDetails userDetails = new UserDetails(firstName, lastName, email, password);
		putUserList(new User(userDetails, role));
		scanner.close();
	}
}
