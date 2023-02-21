package bl.messaging;

import java.util.ArrayList;
import java.util.List;

import core.entity.Complaint;

public class ComplaintService {

	static List<Complaint> complaintList = new ArrayList<>();

	public static List<Complaint> getComplaintList() {
		return complaintList;
	}

	public static void putComplaintList(Complaint complaint) {
		complaintList.add(complaint);
	}
}
