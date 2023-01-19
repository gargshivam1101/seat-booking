package bl.admin;

import java.util.ArrayList;
import java.util.List;

import core.entity.User;

public class UserService {

	static List<User> userList = new ArrayList<>();

	public static List<User> getUserList() {
		return userList;
	}

	public static void putUserList(User user) {
		userList.add(user);
	}

}
