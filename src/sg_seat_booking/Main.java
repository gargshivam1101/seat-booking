package sg_seat_booking;

import java.util.Scanner;

import bl.admin.AdminService;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hi, please login with your registered credentials");

		Scanner scanner = new Scanner(System.in); // java.io.console does not work on Eclipse

		System.out.println("Email Id: ");
		String email = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();

//		scanner.close();

//  TODO: Authenticate and display first name.
		System.out.println("Welcome " + email);

		if (email.equalsIgnoreCase("admin")) {
			AdminService adminService = new AdminService();
			adminService.menu();
		}

	}

}
