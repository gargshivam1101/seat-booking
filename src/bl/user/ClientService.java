package bl.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import bl.booking.RoomService;
import bl.booking.SeatService;
import core.entity.Booking;
import core.entity.Room;

public class ClientService extends UserService {

	@Override
	public void menu() {
		Boolean isLoggedIn = true;
		while (isLoggedIn) {
			System.out.println("✦•······················•✦•······················•✦");
			System.out.println("Please choose an option from the below menu");

			System.out.println("1. Book a seat");
			System.out.println("2. Display my bookings");
			System.out.println("3. Post to resell my seat");
			System.out.println("4. Check and book seats from resell list");
			System.out.println("5. Exchange my seat");
			System.out.println("6. Cancel my booking");
			System.out.println("7. Complain about a booking");
			System.out.println("8. Logout");

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
			case 2:
				List<Booking> myBookings = SeatService
						.getBookingList().stream().filter(b -> b.getUser().getUserDetails()
								.getEmail() == loggedInUser.getUserDetails().getEmail())
						.collect(Collectors.toList());
				for (Booking myBooking : myBookings) {
					System.out.println("ID: " + myBooking.getId() + " for Seat No. ("
							+ myBooking.getSeat().getRow() + "," + myBooking.getSeat().getColumn()
							+ ") and Room No " + myBooking.getSeat().getRoom().getId() + " at "
							+ myBooking.getSeat().getRoom().getBeginTimeStamp());
				}
				break;
			case 3:
				break;
			case 6:
				System.out.println("Enter the ID of the booking you wish to cancel");
				Integer bId = scanner.nextInt();
				SeatService.popBookingList(SeatService.getBookingById(bId));
				System.out.println("Your booking with ID " + bId + " has been cancelled");
				break;
			case 8:
				isLoggedIn = false;
				logout();
				break;
			default:
				System.out.println("Wrong option!");
				break;
			}
		}
	}

	private List<Integer> chooseASeat(Room chosenRoom) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please choose a seat from the below list");
		SeatService.showSeatsByRoom(chosenRoom);
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
		List<Room> eligibleRooms = RoomService.getRoomList().stream()
				.filter(r -> !checkIfOverlap(r.getBeginTimeStamp(), r.getEndTimeStamp()))
				.collect(Collectors.toList());

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please choose a room from below and enter the id");
		for (Room room : eligibleRooms) {
			System.out.println("Room ID: " + room.getId() + ", of type " + room.getRoomType()
					+ " and regular price " + room.getRegularPrice() + ", posted by "
					+ room.getBusinessOwner().getUserDetails().getFirstName() + ", and available from "
					+ room.getBeginTimeStamp() + " to " + room.getEndTimeStamp());
		}
		int choiceOfRoom = scanner.nextInt();
		return RoomService.getRoomById(choiceOfRoom);
	}

	private Boolean checkIfOverlap(LocalDateTime beginTime, LocalDateTime endTime) {
		List<LocalDateTime> unavailabilityOfUser = getBookingTimeStampsOfLoggedInUser();
		for (LocalDateTime time : unavailabilityOfUser) {
			if ((time.isAfter(beginTime) && time.isBefore(endTime))
					|| beginTime.isBefore(LocalDateTime.now())) {
				return true;
			}
		}
		return false;
	}

	private List<LocalDateTime> getBookingTimeStampsOfLoggedInUser() {
		return SeatService.getBookingList().stream().filter(
				b -> b.getUser().getUserDetails().getEmail() == loggedInUser.getUserDetails().getEmail())
				.map(b -> b.getTimestamp()).collect(Collectors.toList());
	}
}
