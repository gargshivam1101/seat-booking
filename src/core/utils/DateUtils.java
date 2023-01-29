package core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateUtils {

	public static LocalDateTime inputDateFromUser(String quesFormat) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the " + quesFormat + " Date in DD-MM-YYYY format");
		String inputDate = scanner.nextLine();
		System.out.println("Enter the " + quesFormat + " Time in HH:MM format");
		String inputTime = scanner.nextLine();

		String timestamp = inputDate + " " + inputTime;

//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime date2 = LocalDateTime.parse(timestamp, dateFormatter);
		System.out.println(date2);
		return date2;
	}
}
