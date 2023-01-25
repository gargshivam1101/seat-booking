package bl.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public static Room getRoomById(Integer id) {
		return RoomService.getRoomList().stream().filter(r -> id == r.getId()).findFirst().orElse(null);
	}

	public static Integer bookRoom(User loggedInUser, RoomType roomType, int rows, double price) {
		Room room = new Room(roomType, rows, price, loggedInUser);
		putRoomList(room);
		return room.getId();
	}
}
