package bl.booking;

import java.util.ArrayList;
import java.util.List;

import core.entity.Room;
import core.entity.User;
import core.enums.RoomType;

public class RoomService {

	static List<Room> roomList = new ArrayList<>();

	public static List<Room> getRoomList() {
		return roomList;
	}

	public static void putRoomList(Room room) {
		roomList.add(room);
	}

	public static void bookRoom(User loggedInUser, RoomType roomType, int rows, int price) {
		Room room = new Room(roomType, rows * rows, price, loggedInUser);
		putRoomList(room);
		System.out.println(getRoomList());
	}
}
