package sg_seat_booking;

import java.util.Scanner;

import bl.admin.AdminService;
import bl.admin.UserService;

public class Main {

	public static void main(String[] args) {

		String email = UserService.login();
		
		if (email.equalsIgnoreCase("admin")) {
			AdminService adminService = new AdminService();
			adminService.menu();
		}

	}

}
