package bl.admin;

import java.util.Scanner;

import bl.booking.RoomService;
import core.enums.RoomType;

public class OwnerService extends UserService {

	@Override
	public void menu() {
		Boolean isLoggedIn = true;
		while (isLoggedIn) {
			System.out.println("✦•······················•✦•······················•✦");
			System.out.println("Please choose an option from the below menu");

			System.out.println("1. Register a room");
			System.out.println("2. Display my registered rooms");
			System.out.println("3. Display my available seats");
			System.out.println("4. Logout");

			Scanner scanner = new Scanner(System.in);
			System.out.println("Press an option: ");
			Integer option = scanner.nextInt();
//			scanner.nextLine();
			switch (option) {
			case 1:
				int type, rows, price;
				System.out.println("Please choose the type of room and enter the respective number");
				for (int i = 0; i < RoomType.values().length; i++) {
					System.out.println((i + 1) + ". " + RoomType.values()[i]);
				}
				type = scanner.nextInt();
				type--;
				System.out.println("Enter the number of rows in the rooms");
				rows = scanner.nextInt();
				System.out.println("Enter the price of a regular seat");
				price = scanner.nextInt();
				RoomService.bookRoom(loggedInUser, RoomType.values()[type], rows, price);
				break;
			case 2:
			case 3:
			case 4:
				isLoggedIn = false;
				logout();
			}
		}
	}
}
