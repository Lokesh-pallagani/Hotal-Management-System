package HMS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class UserSide {

	static HotelSystem.User currentUser;

	static DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	// =====================================================
	// START
	// =====================================================

	static void start() {

		while (true) {

			System.out.println("\n==========================================");

			System.out.println("              USER SIDE");

			System.out.println("==========================================");

			System.out.println("1. User Registration");

			System.out.println("2. User Login");

			System.out.println("3. Back");

			System.out.print("Enter choice: ");

			int choice = HotelSystem.getInt();

			switch (choice) {

			case 1:
				register();
				break;

			case 2:
				login();
				break;

			case 3:
				return;

			default:
				System.out.println("Invalid choice!");
			}
		}
	}

	// =====================================================
	// REGISTER
	// =====================================================

	static void register() {

		System.out.println("\n========== USER REGISTRATION ==========");

		String id = HotelSystem.nextUserId();

		System.out.println("Generated User ID: " + id);

		System.out.print("Enter Name: ");

		String name = HotelSystem.sc.nextLine();

		System.out.print("Enter Mobile Number: ");

		String mobile = HotelSystem.sc.nextLine();

		if (!mobile.matches("\\d{10}")) {

			System.out.println("Invalid mobile number!");

			return;
		}

		System.out.print("Enter Aadhaar Number: ");

		String aadhaar = HotelSystem.sc.nextLine();

		if (!aadhaar.matches("\\d{12}")) {

			System.out.println("Invalid Aadhaar format!");

			return;
		}

		System.out.print("Enter Gender: ");

		String gender = HotelSystem.sc.nextLine();

		System.out.print("Enter City: ");

		String city = HotelSystem.sc.nextLine();

		System.out.print("Create Password: ");

		String password = HotelSystem.sc.nextLine();

		if (password.length() < 6) {

			System.out.println("Password must contain at least 6 characters!");

			return;
		}

		HotelSystem.users.add(new HotelSystem.User(id, name, mobile, aadhaar, gender, city, password));

		// Save immediately
		HotelSystem.saveUsers();

		System.out.println("\nRegistration Successful!");

		System.out.println("User ID: " + id);

		System.out.println("Please use this ID for login.");
	}

	// =====================================================
	// LOGIN
	// =====================================================

	static void login() {

		System.out.println("\n========== USER LOGIN ==========");

		System.out.print("User ID: ");

		String id = HotelSystem.sc.nextLine();

		System.out.print("Password: ");

		String password = HotelSystem.sc.nextLine();

		currentUser = HotelSystem.findUser(id);

		if (currentUser == null || !currentUser.password.equals(password)) {

			currentUser = null;

			System.out.println("Invalid User ID or Password!");

			return;
		}

		System.out.println("\nLogin Successful!");

		System.out.println("Welcome, " + currentUser.name + "!");

		dashboard();
	}

	// =====================================================
	// DASHBOARD
	// =====================================================

	static void dashboard() {

		while (currentUser != null) {

			System.out.println("\n==========================================");

			System.out.println("             USER DASHBOARD");

			System.out.println("==========================================");

			System.out.println("Logged User: " + currentUser.name);

			System.out.println();

			System.out.println("1. Search Rooms");

			System.out.println("2. View Room Details");

			System.out.println("3. Check Room Availability");

			System.out.println("4. Book Room");

			System.out.println("5. Booking Confirmation");

			System.out.println("6. My Bookings");

			System.out.println("7. Check-In");

			System.out.println("8. Hotel Stay");

			System.out.println("9. Check-Out");

			System.out.println("10. View Bill");

			System.out.println("11. Cancel Booking");

			System.out.println("12. Give Feedback");

			System.out.println("13. Logout");

			System.out.print("\nEnter choice: ");

			int choice = HotelSystem.getInt();

			switch (choice) {

			case 1:
				searchRooms();
				break;

			case 2:
				roomDetails();
				break;

			case 3:
				availability();
				break;

			case 4:
				bookRoom();
				break;

			case 5:
				confirmation();
				break;

			case 6:
				myBookings();
				break;

			case 7:
				checkIn();
				break;

			case 8:
				hotelStay();
				break;

			case 9:
				checkOut();
				break;

			case 10:
				bill();
				break;

			case 11:
				cancelBooking();
				break;

			case 12:
				feedback();
				break;

			case 13:

				currentUser = null;

				System.out.println("Logout successful.");

				break;

			default:

				System.out.println("Invalid choice!");
			}
		}
	}

	// =====================================================
	// SEARCH ROOMS
	// =====================================================

	static void searchRooms() {

		System.out.println("\n========== SEARCH ROOMS ==========");

		System.out.println("1. Single");

		System.out.println("2. Double");

		System.out.println("3. Triple");

		System.out.println("4. Deluxe");

		System.out.println("5. Suite");

		System.out.println("6. AC");

		System.out.println("7. NON-AC");

		System.out.print("Enter choice: ");

		int choice = HotelSystem.getInt();

		boolean found = false;

		for (HotelSystem.Room room : HotelSystem.rooms) {

			boolean match = false;

			if (choice == 1 && room.type.equalsIgnoreCase("Single"))
				match = true;

			else if (choice == 2 && room.type.equalsIgnoreCase("Double"))
				match = true;

			else if (choice == 3 && room.type.equalsIgnoreCase("Triple"))
				match = true;

			else if (choice == 4 && room.type.equalsIgnoreCase("Deluxe"))
				match = true;

			else if (choice == 5 && room.type.equalsIgnoreCase("Suite"))
				match = true;

			else if (choice == 6 && room.ac.equalsIgnoreCase("AC"))
				match = true;

			else if (choice == 7 && room.ac.equalsIgnoreCase("NON-AC"))
				match = true;

			if (match) {

				HotelSystem.showRoom(room);

				found = true;
			}
		}

		if (!found) {

			System.out.println("No matching rooms found.");
		}
	}

	// =====================================================
	// ROOM DETAILS
	// =====================================================

	static void roomDetails() {

		System.out.print("\nEnter Room Number: ");

		String number = HotelSystem.sc.nextLine();

		HotelSystem.Room room = HotelSystem.findRoom(number);

		if (room == null) {

			System.out.println("Room not found!");

			return;
		}

		HotelSystem.showRoom(room);
	}

	// =====================================================
	// AVAILABILITY
	// =====================================================

	static void availability() {

		System.out.println("\n========== AVAILABLE ROOMS ==========");

		boolean found = false;

		for (HotelSystem.Room room : HotelSystem.rooms) {

			if (room.available) {

				HotelSystem.showRoom(room);

				found = true;
			}
		}

		if (!found) {

			System.out.println("No rooms are currently available.");
		}
	}

	// =====================================================
	// BOOK ROOM
	// =====================================================

	static void bookRoom() {

		System.out.println("\n========== ROOM BOOKING ==========");

		availability();

		System.out.print("\nEnter Room Number: ");

		String number = HotelSystem.sc.nextLine();

		HotelSystem.Room room = HotelSystem.findRoom(number);

		if (room == null) {

			System.out.println("Room not found!");

			return;
		}

		if (!room.available) {

			System.out.println("This room is currently occupied.");

			return;
		}

		try {

			System.out.print("Check-In Date (DD-MM-YYYY): ");

			LocalDate checkIn = LocalDate.parse(HotelSystem.sc.nextLine(), DF);

			System.out.print("Check-Out Date (DD-MM-YYYY): ");

			LocalDate checkOut = LocalDate.parse(HotelSystem.sc.nextLine(), DF);

			if (!checkOut.isAfter(checkIn)) {

				System.out.println("Check-out must be after check-in.");

				return;
			}

			long days = ChronoUnit.DAYS.between(checkIn, checkOut);

			System.out.print("Number of Guests: ");

			int guests = HotelSystem.getInt();

			if (guests <= 0) {

				System.out.println("Invalid number of guests.");

				return;
			}

			if (guests > room.capacity) {

				System.out.println("Maximum capacity is " + room.capacity);

				return;
			}

			double roomCost = room.price * days;

			double gst = roomCost * 0.05;

			double total = roomCost + gst;

			System.out.println("\n========== BOOKING SUMMARY ==========");

			System.out.println("Customer       : " + currentUser.name);

			System.out.println("Room           : " + room.number);

			System.out.println("Room Type      : " + room.type);

			System.out.println("Check-In       : " + checkIn.format(DF));

			System.out.println("Check-Out      : " + checkOut.format(DF));

			System.out.println("Number of Days : " + days);

			System.out.println("Guests         : " + guests);

			System.out.printf("Room Cost      : Rs. %.2f%n", roomCost);

			System.out.printf("GST (5%%)       : Rs. %.2f%n", gst);

			System.out.printf("Total Amount   : Rs. %.2f%n", total);

			System.out.print("\nConfirm Booking? (YES/NO): ");

			String confirm = HotelSystem.sc.nextLine();

			if (!confirm.equalsIgnoreCase("YES")) {

				System.out.println("Booking cancelled.");

				return;
			}

			System.out.println("\n========== PAYMENT ==========");

			System.out.println("1. UPI");

			System.out.println("2. Debit Card");

			System.out.println("3. Credit Card");

			System.out.println("4. Cash");

			System.out.print("Select Payment Method: ");

			int payment = HotelSystem.getInt();

			String method;

			switch (payment) {

			case 1:
				method = "UPI";
				break;

			case 2:
				method = "Debit Card";
				break;

			case 3:
				method = "Credit Card";
				break;

			case 4:
				method = "Cash";
				break;

			default:

				System.out.println("Invalid payment method.");

				return;
			}

			String bookingId = HotelSystem.nextBookingId();

			HotelSystem.Booking booking = new HotelSystem.Booking(bookingId, currentUser.id, currentUser.name,
					room.number, room.type, room.ac, checkIn.format(DF), checkOut.format(DF), guests, days, roomCost,
					gst, total, "CONFIRMED", "PAID", method);

			HotelSystem.bookings.add(booking);

			room.available = false;

			// SAVE TO FILE
			HotelSystem.saveBookings();
			HotelSystem.saveRooms();

			System.out.println("\n==========================================");

			System.out.println("           BOOKING SUCCESSFUL");

			System.out.println("==========================================");

			System.out.println("Booking ID : " + bookingId);

			System.out.println("Customer   : " + currentUser.name);

			System.out.println("Room       : " + room.number);

			System.out.printf("Paid       : Rs. %.2f%n", total);

			System.out.println("Payment    : " + method);

			System.out.println("Status     : CONFIRMED");

			System.out.println("Data saved to database file.");

			System.out.println("==========================================");

		} catch (Exception e) {

			System.out.println("Invalid date format!");

			System.out.println("Use DD-MM-YYYY.");
		}
	}

	// =====================================================
	// LATEST BOOKING
	// =====================================================

	static HotelSystem.Booking latestBooking() {

		HotelSystem.Booking latest = null;

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			if (booking.userId.equals(currentUser.id)) {

				latest = booking;
			}
		}

		return latest;
	}

	// =====================================================
	// CONFIRMATION
	// =====================================================

	static void confirmation() {

		HotelSystem.Booking booking = latestBooking();

		if (booking == null) {

			System.out.println("No booking found.");

			return;
		}

		HotelSystem.showBooking(booking);
	}

	// =====================================================
	// MY BOOKINGS
	// =====================================================

	static void myBookings() {

		boolean found = false;

		System.out.println("\n========== MY BOOKINGS ==========");

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			if (booking.userId.equals(currentUser.id)) {

				HotelSystem.showBooking(booking);

				found = true;
			}
		}

		if (!found) {

			System.out.println("No bookings found.");
		}
	}

	// =====================================================
	// ACTIVE BOOKING
	// =====================================================

	static HotelSystem.Booking activeBooking() {

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			if (booking.userId.equals(currentUser.id) && !booking.status.equalsIgnoreCase("CANCELLED")
					&& !booking.checkedOut) {

				return booking;
			}
		}

		return null;
	}

	// =====================================================
	// CHECK-IN
	// =====================================================

	static void checkIn() {

		HotelSystem.Booking booking = activeBooking();

		if (booking == null) {

			System.out.println("No active booking found.");

			return;
		}

		if (booking.checkedIn) {

			System.out.println("You are already checked in.");

			return;
		}

		booking.checkedIn = true;

		booking.status = "CHECKED-IN";

		HotelSystem.saveBookings();

		System.out.println("\nCheck-In Successful!");

		System.out.println("Data saved successfully.");
	}

	// =====================================================
	// HOTEL STAY
	// =====================================================

	static void hotelStay() {

		HotelSystem.Booking booking = activeBooking();

		if (booking == null || !booking.checkedIn) {

			System.out.println("You do not have an active hotel stay.");

			return;
		}

		System.out.println("\n========== CURRENT HOTEL STAY ==========");

		System.out.println("Customer    : " + currentUser.name);

		System.out.println("Room        : " + booking.roomNumber);

		System.out.println("Room Type   : " + booking.roomType);

		System.out.println("Check-In    : " + booking.checkIn);

		System.out.println("Check-Out   : " + booking.checkOut);

		System.out.println("Status      : " + booking.status);
	}

	// =====================================================
	// CHECK-OUT
	// =====================================================

	static void checkOut() {

		HotelSystem.Booking booking = activeBooking();

		if (booking == null) {

			System.out.println("No active booking found.");

			return;
		}

		if (!booking.checkedIn) {

			System.out.println("You must check-in before check-out.");

			return;
		}

		booking.checkedOut = true;

		booking.status = "CHECKED-OUT";

		HotelSystem.Room room = HotelSystem.findRoom(booking.roomNumber);

		if (room != null) {

			room.available = true;
		}

		HotelSystem.saveBookings();
		HotelSystem.saveRooms();

		System.out.println("\nCheck-Out Successful!");

		System.out.println("Room is now available.");

		System.out.println("Data saved successfully.");
	}

	// =====================================================
	// BILL
	// =====================================================

	static void bill() {

		System.out.print("\nEnter Booking ID: ");

		String id = HotelSystem.sc.nextLine();

		HotelSystem.Booking booking = HotelSystem.findBooking(id);

		if (booking == null || !booking.userId.equals(currentUser.id)) {

			System.out.println("Booking not found.");

			return;
		}

		HotelSystem.showBooking(booking);
	}

	// =====================================================
	// CANCEL BOOKING
	// =====================================================

	static void cancelBooking() {

		System.out.print("\nEnter Booking ID: ");

		String id = HotelSystem.sc.nextLine();

		HotelSystem.Booking booking = HotelSystem.findBooking(id);

		if (booking == null || !booking.userId.equals(currentUser.id)) {

			System.out.println("Booking not found.");

			return;
		}

		if (booking.checkedOut) {

			System.out.println("Checked-out booking cannot be cancelled.");

			return;
		}

		if (booking.status.equalsIgnoreCase("CANCELLED")) {

			System.out.println("Booking is already cancelled.");

			return;
		}

		booking.status = "CANCELLED";

		HotelSystem.Room room = HotelSystem.findRoom(booking.roomNumber);

		if (room != null) {

			room.available = true;
		}

		HotelSystem.saveBookings();
		HotelSystem.saveRooms();

		System.out.println("Booking cancelled successfully.");

		System.out.println("Data saved successfully.");
	}

	// =====================================================
	// FEEDBACK
	// =====================================================

	static void feedback() {

		System.out.println("\n========== FEEDBACK ==========");

		System.out.print("Enter your feedback: ");

		String feedback = HotelSystem.sc.nextLine();

		if (feedback.trim().isEmpty()) {

			System.out.println("Feedback cannot be empty.");

			return;
		}

		System.out.println("\nThank you for your valuable feedback!");

		System.out.println("Your Feedback: " + feedback);
	}
}