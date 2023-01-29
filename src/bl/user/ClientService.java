package bl.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bl.booking.RoomService;
import bl.booking.SeatService;
import core.entity.Room;

public class ClientService extends UserService {

	@Override
	public void menu() {
		Boolean isLoggedIn = true;
		while (isLoggedIn) {
			System.out.println("✦•······················•✦•······················•✦");
			System.out.println("Please choose an option from the below menu");

			System.out.println("1. Book a seat");
			System.out.println("2. Display my booked seats");
			System.out.println("3. Complain about a booking");
			System.out.println("4. Logout");

			Scanner scanner = new Scanner(System.in);
			System.out.println("Press an option: ");
			Integer option = scanner.nextInt();
//		scanner.nextLine();
			switch (option) {
			case 1:
				Room chosenRoom = chooseARoom();
				List<Integer> chosenSeat = chooseASeat(chosenRoom);
				int bookingId = SeatService.bookSeat(loggedInUser, chosenRoom, chosenSeat.get(0),
						chosenSeat.get(1));
				System.out.println("Your seat (" + chosenSeat.get(0) + "," + chosenSeat.get(1)
						+ ") has been booked. The Booking ID is " + bookingId);
				break;
			case 4:
				isLoggedIn = false;
				logout();
				break;
			default:
				System.out.println("Wrong option!");
				break;
			}
		}
	}

//	private void checkIfSeatIsAvailable(Seat seat) {
//		
//	}

	private List<Integer> chooseASeat(Room chosenRoom) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please choose a seat from the below list");
		for (int i = 0; i < chosenRoom.getSize(); i++) {
			for (int j = 0; j < chosenRoom.getSize(); j++) {
				System.out.print("(" + i + "," + j + ")  ");
			}
			System.out.println();
		}
		String choiceOfSeat = scanner.nextLine();
		int row = Integer.parseInt(choiceOfSeat.split(",")[0]);
		int column = Integer.parseInt(choiceOfSeat.split(",")[1]);
		return new ArrayList<>() {
			{
				add(row);
				add(column);
			}
		};
	}

	private Room chooseARoom() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please choose a room from below and enter the id");
		for (Room room : RoomService.getRoomList()) {
			System.out.println("Room ID: " + room.getId() + ", of type " + room.getRoomType()
					+ " and regular price " + room.getRegularPrice() + ", posted by "
					+ room.getBusinessOwner().getUserDetails().getFirstName() + ", and available from "
					+ room.getBeginTimeStamp() + " to " + room.getEndTimeStamp());
		}
		int choiceOfRoom = scanner.nextInt();
		return RoomService.getRoomById(choiceOfRoom);
	}
}
