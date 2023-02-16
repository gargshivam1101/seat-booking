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
			System.out.println("4. Cancel my booking");
			System.out.println("5. Complain about a booking");
			System.out.println("6. Logout");

			Scanner scanner = new Scanner(System.in);
			System.out.println("Press an option: ");
			Integer option = scanner.nextInt();
//		scanner.nextLine();
			switch (option) {
			case 1:
				System.out.println("1. Book a new seat");
				System.out.println("2. Book from resell list");
				System.out.println("3. Exhange my seat");
				Integer typeOfBooking = scanner.nextInt();
				switch (typeOfBooking) {
				case 1:
					bookANewSeat();
					break;
				case 2:
					bookFromResellList();
					break;
				case 3:
				default:
					System.out.println("Wrong input");
				}
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
				System.out.println("Enter the booking id");
				Integer inputBookingId = scanner.nextInt();
				Booking inputBooking = SeatService.getBookingById(inputBookingId);
				Double newPrice = calculateNewPrice(inputBooking.getPrice());
				SeatService.popBookingList(inputBooking);
				inputBooking.setPrice(newPrice);
				inputBooking.setUser(null);
				SeatService.putResellList(inputBooking);
				System.out
						.println("Your booking with id " + inputBookingId + " has been posted for reselling");
				break;
			case 4:
				System.out.println("Enter the ID of the booking you wish to cancel");
				Integer bId = scanner.nextInt();
				SeatService.popBookingList(SeatService.getBookingById(bId));
				System.out.println("Your booking with ID " + bId + " has been cancelled");
				break;
			case 6:
				isLoggedIn = false;
				logout();
				break;
			default:
				System.out.println("Wrong option!");
				break;
			}
		}
	}

	private Double calculateNewPrice(Double oldPrice) {
		System.out.println("Either enter the new price or the discount ratio in %");
		Scanner scanner = new Scanner(System.in);
		String newValue = scanner.nextLine();
		Double newPrice = oldPrice;
		if (newValue.contains("%")) {
			Double discountRatio = (Double.parseDouble(newValue.substring(0, newValue.length() - 1)))
					/ 100;
			newPrice = oldPrice * (1 - (discountRatio));
		} else {
			newPrice = Double.parseDouble(newValue);
		}
		return newPrice;
	}

	private void bookFromResellList() {
		System.out.println("1. Look for available seats");
		System.out.println("2. Check status of ongoing negotiations");
		Scanner scanner = new Scanner(System.in);
		Integer input = scanner.nextInt();
		switch (input) {
		case 1:
			System.out.println();
			System.out.println("The following seats are available in resell list");
			for (Booking booking : SeatService.getResellList()) {
				System.out.println("Booking ID " + booking.getId() + ", Seat No. ("
						+ booking.getSeat().getRow() + "," + booking.getSeat().getColumn()
						+ ") in Room of type " + booking.getSeat().getRoom().getRoomType() + " at "
						+ booking.getSeat().getRoom().getBeginTimeStamp() + " for $" + booking.getPrice());
			}
			System.out.println("Enter the Booking ID you are interested in");
			Integer bookingId = scanner.nextInt();
			Booking booking = SeatService.getResellBookingById(bookingId);

			if (booking == null) {
				System.out.println("Error occurred. Please try again.");
				return;
			}

			System.out.println("Do you want to book it or negotiate further?");
			System.out.println("Enter B to book and N to negotiate");
			scanner.nextLine();
			String choiceOfBooking = scanner.nextLine();
			if ("B".equalsIgnoreCase(choiceOfBooking)) {
				SeatService.popResellList(booking);
				booking.setUser(loggedInUser);
				SeatService.putBookingList(booking);
				System.out.println("Your booking has been made");
			} else if ("N".equalsIgnoreCase(choiceOfBooking)) {
				// Negotiate
			} else {
				System.out.println("Wrong input");
			}
			break;
		case 2:
			break;
		default:
			System.out.println("Wrong option");
		}

	}

	private void bookANewSeat() {
		Room chosenRoom = chooseARoom();
		List<Integer> chosenSeat = chooseASeat(chosenRoom);
		int bookingId = SeatService.bookSeat(loggedInUser, chosenRoom, chosenSeat.get(0),
				chosenSeat.get(1));
		System.out.println("Your seat (" + chosenSeat.get(0) + "," + chosenSeat.get(1)
				+ ") has been booked. The Booking ID is " + bookingId);
	}

	private List<Integer> getRowAndColumnFromSeatNo(String seatNo) {
		int row = Integer.parseInt(seatNo.split(",")[0]);
		int column = Integer.parseInt(seatNo.split(",")[1]);
		return new ArrayList<>() {
			{
				add(row);
				add(column);
			}
		};

	}

	private List<Integer> chooseASeat(Room chosenRoom) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please choose a seat from the below list");
		SeatService.showSeatsByRoom(chosenRoom);
		String choiceOfSeat = scanner.nextLine();
		return getRowAndColumnFromSeatNo(choiceOfSeat);
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
