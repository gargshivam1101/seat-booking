package sg_seat_booking;

import bl.admin.UserService;

public class Main {

	public static void main(String[] args) {

		while (true) {
			UserService.login();
		}
	}
}
