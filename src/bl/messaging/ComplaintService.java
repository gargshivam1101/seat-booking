package bl.messaging;

import java.util.ArrayList;
import java.util.List;

import core.entity.Booking;
import core.entity.Complaint;
import core.entity.User;

public class ComplaintService {

	static List<Complaint> complaintList = new ArrayList<>();

	public static List<Complaint> getComplaintList() {
		return complaintList;
	}

	public static void putComplaintList(Complaint complaint) {
		complaintList.add(complaint);
	}

	public static Integer registerComplaint(User user, Booking booking, String description) {
		String title = "Complaint for Booking ID: " + booking.getId();
		Complaint complaint = new Complaint(title, description, user);
		return complaint.getId();
	}
}
