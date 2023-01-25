package bl.booking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import core.entity.Booking;
import core.entity.Room;
import core.entity.Seat;
import core.entity.User;

public class SeatService {

	static List<Booking> bookingList = new ArrayList<>();

	public static List<Booking> getBookingList() {
		return bookingList;
	}

	public static void putBookingList(Booking booking) {
		bookingList.add(booking);
	}

	public static Integer bookSeat(User loggedInUser, Room chosenRoom, int row, int column) {
		Seat seat = new Seat(row, column, chosenRoom);
		Double regularPrice = chosenRoom.getRegularPrice();
		Integer sizeOfRoom = chosenRoom.getSize();
		Double price = regularPrice;
		if (row == 0) {
			price = 2 * regularPrice;
		} else if (row == sizeOfRoom - 1) {
			price = 0.75 * regularPrice;
		} else if (column == ((sizeOfRoom - 1) / 2) || column == ((sizeOfRoom) / 2)) {
			price = 1.25 * regularPrice;
		} else {
			price = regularPrice;
		}
		
		Booking booking = new Booking(seat, price, LocalDateTime.now(), loggedInUser);
		putBookingList(booking);
		return booking.getId();
	}
}
