package HMS;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class HotelSystem {

	static Scanner sc = new Scanner(System.in);

	// =====================================================
	// DATA LISTS
	// =====================================================

	static ArrayList<User> users = new ArrayList<>();
	static ArrayList<Room> rooms = new ArrayList<>();
	static ArrayList<Booking> bookings = new ArrayList<>();
	static ArrayList<Employee> employees = new ArrayList<>();

	// =====================================================
	// FILE PATHS
	// =====================================================

	static final String DATA_FOLDER = "data";

	static final String USERS_FILE = DATA_FOLDER + File.separator + "users.txt";

	static final String ROOMS_FILE = DATA_FOLDER + File.separator + "rooms.txt";

	static final String EMPLOYEES_FILE = DATA_FOLDER + File.separator + "employees.txt";

	static final String BOOKINGS_FILE = DATA_FOLDER + File.separator + "bookings.txt";

	// =====================================================
	// ID COUNTERS
	// =====================================================

	static int userNo = 1;
	static int bookingNo = 1;
	static int employeeNo = 1;

	// =====================================================
	// USER CLASS
	// =====================================================

	static class User {

		String id;
		String name;
		String mobile;
		String aadhaar;
		String gender;
		String city;
		String password;

		User(String id, String name, String mobile, String aadhaar, String gender, String city, String password) {

			this.id = id;
			this.name = name;
			this.mobile = mobile;
			this.aadhaar = aadhaar;
			this.gender = gender;
			this.city = city;
			this.password = password;
		}

		String toFileString() {

			return id + "|" + name + "|" + mobile + "|" + aadhaar + "|" + gender + "|" + city + "|" + password;
		}
	}

	// =====================================================
	// ROOM CLASS
	// =====================================================

	static class Room {

		String number;
		String type;
		String ac;

		int capacity;

		double price;

		boolean available;

		Room(String number, String type, String ac, int capacity, double price) {

			this.number = number;
			this.type = type;
			this.ac = ac;
			this.capacity = capacity;
			this.price = price;

			this.available = true;
		}

		Room(String number, String type, String ac, int capacity, double price, boolean available) {

			this.number = number;
			this.type = type;
			this.ac = ac;
			this.capacity = capacity;
			this.price = price;

			this.available = available;
		}

		String toFileString() {

			return number + "|" + type + "|" + ac + "|" + capacity + "|" + price + "|" + available;
		}
	}

	// =====================================================
	// BOOKING CLASS
	// =====================================================

	static class Booking {

		String id;

		String userId;
		String userName;

		String roomNumber;
		String roomType;
		String ac;

		String checkIn;
		String checkOut;

		int guests;

		long days;

		double roomCost;
		double gst;
		double total;

		String status;
		String paymentStatus;
		String paymentMethod;

		boolean checkedIn;
		boolean checkedOut;

		Booking(String id, String userId, String userName, String roomNumber, String roomType, String ac,
				String checkIn, String checkOut, int guests, long days, double roomCost, double gst, double total,
				String status, String paymentStatus, String paymentMethod) {

			this.id = id;

			this.userId = userId;
			this.userName = userName;

			this.roomNumber = roomNumber;
			this.roomType = roomType;
			this.ac = ac;

			this.checkIn = checkIn;
			this.checkOut = checkOut;

			this.guests = guests;

			this.days = days;

			this.roomCost = roomCost;
			this.gst = gst;
			this.total = total;

			this.status = status;
			this.paymentStatus = paymentStatus;
			this.paymentMethod = paymentMethod;

			this.checkedIn = false;
			this.checkedOut = false;
		}

		Booking(String id, String userId, String userName, String roomNumber, String roomType, String ac,
				String checkIn, String checkOut, int guests, long days, double roomCost, double gst, double total,
				String status, String paymentStatus, String paymentMethod, boolean checkedIn, boolean checkedOut) {

			this.id = id;

			this.userId = userId;
			this.userName = userName;

			this.roomNumber = roomNumber;
			this.roomType = roomType;
			this.ac = ac;

			this.checkIn = checkIn;
			this.checkOut = checkOut;

			this.guests = guests;
			this.days = days;

			this.roomCost = roomCost;
			this.gst = gst;
			this.total = total;

			this.status = status;
			this.paymentStatus = paymentStatus;
			this.paymentMethod = paymentMethod;

			this.checkedIn = checkedIn;
			this.checkedOut = checkedOut;
		}

		String toFileString() {

			return id + "|" + userId + "|" + userName + "|" + roomNumber + "|" + roomType + "|" + ac + "|" + checkIn
					+ "|" + checkOut + "|" + guests + "|" + days + "|" + roomCost + "|" + gst + "|" + total + "|"
					+ status + "|" + paymentStatus + "|" + paymentMethod + "|" + checkedIn + "|" + checkedOut;
		}
	}

	// =====================================================
	// EMPLOYEE CLASS
	// =====================================================

	static class Employee {

		String id;
		String name;
		String role;
		String mobile;

		double salary;

		Employee(String id, String name, String role, String mobile, double salary) {

			this.id = id;
			this.name = name;
			this.role = role;
			this.mobile = mobile;
			this.salary = salary;
		}

		String toFileString() {

			return id + "|" + name + "|" + role + "|" + mobile + "|" + salary;
		}
	}

	// =====================================================
	// INITIALIZE DATABASE
	// =====================================================

	static void initializeDatabase() {

		try {

			File folder = new File(DATA_FOLDER);

			if (!folder.exists()) {

				folder.mkdirs();

				System.out.println("Data folder created successfully.");
			}

			createFileIfNotExists(USERS_FILE);
			createFileIfNotExists(ROOMS_FILE);
			createFileIfNotExists(EMPLOYEES_FILE);
			createFileIfNotExists(BOOKINGS_FILE);

			// Add sample data only when files are empty

			if (isFileEmpty(USERS_FILE)) {
				createDefaultUsers();
			}

			if (isFileEmpty(ROOMS_FILE)) {
				createDefaultRooms();
			}

			if (isFileEmpty(EMPLOYEES_FILE)) {
				createDefaultEmployees();
			}

			if (isFileEmpty(BOOKINGS_FILE)) {
				createDefaultBookings();
			}

		} catch (Exception e) {

			System.out.println("Database initialization error: " + e.getMessage());
		}
	}

	// =====================================================
	// CREATE FILE
	// =====================================================

	static void createFileIfNotExists(String fileName) throws IOException {

		File file = new File(fileName);

		if (!file.exists()) {

			file.createNewFile();
		}
	}

	// =====================================================
	// CHECK EMPTY FILE
	// =====================================================

	static boolean isFileEmpty(String fileName) {

		File file = new File(fileName);

		return file.length() == 0;
	}

	// =====================================================
	// DEFAULT USERS
	// =====================================================

	static void createDefaultUsers() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE));

			writer.write("U001|Rahul Kumar|9876543210|000000000001|Male|Vijayawada|rahul123");

			writer.newLine();

			writer.write("U002|Priya Sharma|9123456780|000000000002|Female|Hyderabad|priya123");

			writer.newLine();

			writer.write("U003|Arjun Reddy|9988776655|000000000003|Male|Guntur|arjun123");

			writer.newLine();

			writer.write("U004|Sneha Patel|9012345678|000000000004|Female|Bangalore|sneha123");

			writer.newLine();

			writer.write("U005|Kiran Kumar|9345678120|000000000005|Male|Chennai|kiran123");

			writer.newLine();

			writer.close();

		} catch (IOException e) {

			System.out.println("Unable to create users data.");
		}
	}

	// =====================================================
	// DEFAULT ROOMS
	// =====================================================

	static void createDefaultRooms() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(ROOMS_FILE));

			writer.write("101|Single|AC|1|1500.0|true");

			writer.newLine();

			writer.write("102|Single|NON-AC|1|1000.0|true");

			writer.newLine();

			writer.write("103|Single|AC|1|1500.0|true");

			writer.newLine();

			writer.write("201|Double|AC|2|2500.0|false");

			writer.newLine();

			writer.write("202|Double|NON-AC|2|2000.0|true");

			writer.newLine();

			writer.write("203|Double|AC|2|2500.0|true");

			writer.newLine();

			writer.write("301|Triple|AC|3|3500.0|false");

			writer.newLine();

			writer.write("302|Triple|NON-AC|3|3000.0|true");

			writer.newLine();

			writer.write("401|Deluxe|AC|4|4500.0|false");

			writer.newLine();

			writer.write("501|Suite|AC|5|6000.0|true");

			writer.newLine();

			writer.close();

		} catch (IOException e) {

			System.out.println("Unable to create rooms data.");
		}
	}

	// =====================================================
	// DEFAULT EMPLOYEES
	// =====================================================

	static void createDefaultEmployees() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEES_FILE));

			writer.write("E001|Ramesh Kumar|Manager|9000000001|45000.0");

			writer.newLine();

			writer.write("E002|Suresh Rao|Receptionist|9000000002|28000.0");

			writer.newLine();

			writer.write("E003|Anjali Singh|Accountant|9000000003|32000.0");

			writer.newLine();

			writer.write("E004|Vikram Das|Housekeeping|9000000004|22000.0");

			writer.newLine();

			writer.write("E005|Meena Devi|Cook|9000000005|25000.0");

			writer.newLine();

			writer.close();

		} catch (IOException e) {

			System.out.println("Unable to create employee data.");
		}
	}

	// =====================================================
	// DEFAULT BOOKINGS
	// =====================================================

	static void createDefaultBookings() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE));

			writer.write(
					"B001|U001|Rahul Kumar|201|Double|AC|20-08-2026|22-08-2026|2|2|5000.0|250.0|5250.0|CONFIRMED|PAID|UPI|false|false");

			writer.newLine();

			writer.write(
					"B002|U002|Priya Sharma|301|Triple|AC|21-08-2026|24-08-2026|3|3|10500.0|525.0|11025.0|CONFIRMED|PAID|Debit Card|false|false");

			writer.newLine();

			writer.write(
					"B003|U003|Arjun Reddy|401|Deluxe|AC|22-08-2026|23-08-2026|2|1|4500.0|225.0|4725.0|CONFIRMED|PAID|Credit Card|false|false");

			writer.newLine();

			writer.close();

		} catch (IOException e) {

			System.out.println("Unable to create booking data.");
		}
	}

	// =====================================================
	// LOAD ALL DATA
	// =====================================================

	static void loadAllData() {

		users.clear();
		rooms.clear();
		employees.clear();
		bookings.clear();

		loadUsers();
		loadRooms();
		loadEmployees();
		loadBookings();

		updateCounters();

		System.out.println("Database loaded successfully.");
	}

	// =====================================================
	// LOAD USERS
	// =====================================================

	static void loadUsers() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));

			String line;

			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty())
					continue;

				String[] data = line.split("\\|");

				if (data.length >= 7) {

					users.add(new User(data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
				}
			}

			reader.close();

		} catch (IOException e) {

			System.out.println("Error loading users: " + e.getMessage());
		}
	}

	// =====================================================
	// LOAD ROOMS
	// =====================================================

	static void loadRooms() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(ROOMS_FILE));

			String line;

			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty())
					continue;

				String[] data = line.split("\\|");

				if (data.length >= 6) {

					rooms.add(new Room(data[0], data[1], data[2], Integer.parseInt(data[3]),
							Double.parseDouble(data[4]), Boolean.parseBoolean(data[5])));
				}
			}

			reader.close();

		} catch (Exception e) {

			System.out.println("Error loading rooms: " + e.getMessage());
		}
	}

	// =====================================================
	// LOAD EMPLOYEES
	// =====================================================

	static void loadEmployees() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEES_FILE));

			String line;

			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty())
					continue;

				String[] data = line.split("\\|");

				if (data.length >= 5) {

					employees.add(new Employee(data[0], data[1], data[2], data[3], Double.parseDouble(data[4])));
				}
			}

			reader.close();

		} catch (Exception e) {

			System.out.println("Error loading employees: " + e.getMessage());
		}
	}

	// =====================================================
	// LOAD BOOKINGS
	// =====================================================

	static void loadBookings() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(BOOKINGS_FILE));

			String line;

			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty())
					continue;

				String[] data = line.split("\\|");

				if (data.length >= 18) {

					bookings.add(new Booking(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7],
							Integer.parseInt(data[8]), Long.parseLong(data[9]), Double.parseDouble(data[10]),
							Double.parseDouble(data[11]), Double.parseDouble(data[12]), data[13], data[14], data[15],
							Boolean.parseBoolean(data[16]), Boolean.parseBoolean(data[17])));
				}
			}

			reader.close();

		} catch (Exception e) {

			System.out.println("Error loading bookings: " + e.getMessage());
		}
	}

	// =====================================================
	// SAVE ALL DATA
	// =====================================================

	static void saveAllData() {

		saveUsers();
		saveRooms();
		saveEmployees();
		saveBookings();
	}

	// =====================================================
	// SAVE USERS
	// =====================================================

	static void saveUsers() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE));

			for (User user : users) {

				writer.write(user.toFileString());

				writer.newLine();
			}

			writer.close();

		} catch (IOException e) {

			System.out.println("Error saving users.");
		}
	}

	// =====================================================
	// SAVE ROOMS
	// =====================================================

	static void saveRooms() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(ROOMS_FILE));

			for (Room room : rooms) {

				writer.write(room.toFileString());

				writer.newLine();
			}

			writer.close();

		} catch (IOException e) {

			System.out.println("Error saving rooms.");
		}
	}

	// =====================================================
	// SAVE EMPLOYEES
	// =====================================================

	static void saveEmployees() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEES_FILE));

			for (Employee employee : employees) {

				writer.write(employee.toFileString());

				writer.newLine();
			}

			writer.close();

		} catch (IOException e) {

			System.out.println("Error saving employees.");
		}
	}

	// =====================================================
	// SAVE BOOKINGS
	// =====================================================

	static void saveBookings() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS_FILE));

			for (Booking booking : bookings) {

				writer.write(booking.toFileString());

				writer.newLine();
			}

			writer.close();

		} catch (IOException e) {

			System.out.println("Error saving bookings.");
		}
	}

	// =====================================================
	// UPDATE COUNTERS
	// =====================================================

	static void updateCounters() {

		int maxUser = 0;

		int maxBooking = 0;

		int maxEmployee = 0;

		for (User user : users) {

			try {

				int n = Integer.parseInt(user.id.substring(1));

				if (n > maxUser)
					maxUser = n;

			} catch (Exception ignored) {
			}
		}

		for (Booking booking : bookings) {

			try {

				int n = Integer.parseInt(booking.id.substring(1));

				if (n > maxBooking)
					maxBooking = n;

			} catch (Exception ignored) {
			}
		}

		for (Employee employee : employees) {

			try {

				int n = Integer.parseInt(employee.id.substring(1));

				if (n > maxEmployee)
					maxEmployee = n;

			} catch (Exception ignored) {
			}
		}

		userNo = maxUser + 1;

		bookingNo = maxBooking + 1;

		employeeNo = maxEmployee + 1;
	}

	// =====================================================
	// INPUT INTEGER
	// =====================================================

	static int getInt() {

		while (true) {

			try {

				return Integer.parseInt(sc.nextLine().trim());

			} catch (Exception e) {

				System.out.print("Enter a valid number: ");
			}
		}
	}

	// =====================================================
	// INPUT DOUBLE
	// =====================================================

	static double getDouble() {

		while (true) {

			try {

				return Double.parseDouble(sc.nextLine().trim());

			} catch (Exception e) {

				System.out.print("Enter a valid amount: ");
			}
		}
	}

	// =====================================================
	// FIND USER
	// =====================================================

	static User findUser(String id) {

		for (User user : users) {

			if (user.id.equalsIgnoreCase(id)) {

				return user;
			}
		}

		return null;
	}

	// =====================================================
	// FIND ROOM
	// =====================================================

	static Room findRoom(String number) {

		for (Room room : rooms) {

			if (room.number.equalsIgnoreCase(number)) {

				return room;
			}
		}

		return null;
	}

	// =====================================================
	// FIND BOOKING
	// =====================================================

	static Booking findBooking(String id) {

		for (Booking booking : bookings) {

			if (booking.id.equalsIgnoreCase(id)) {

				return booking;
			}
		}

		return null;
	}

	// =====================================================
	// FIND EMPLOYEE
	// =====================================================

	static Employee findEmployee(String id) {

		for (Employee employee : employees) {

			if (employee.id.equalsIgnoreCase(id)) {

				return employee;
			}
		}

		return null;
	}

	// =====================================================
	// GENERATE USER ID
	// =====================================================

	static String nextUserId() {

		return String.format("U%03d", userNo++);
	}

	// =====================================================
	// GENERATE BOOKING ID
	// =====================================================

	static String nextBookingId() {

		return String.format("B%03d", bookingNo++);
	}

	// =====================================================
	// GENERATE EMPLOYEE ID
	// =====================================================

	static String nextEmployeeId() {

		return String.format("E%03d", employeeNo++);
	}

	// =====================================================
	// DISPLAY ROOM
	// =====================================================

	static void showRoom(Room room) {

		System.out.println("\n------------------------------------------");

		System.out.println("Room Number : " + room.number);

		System.out.println("Room Type   : " + room.type);

		System.out.println("AC Type     : " + room.ac);

		System.out.println("Capacity    : " + room.capacity);

		System.out.printf("Price/Day   : Rs. %.2f%n", room.price);

		System.out.println("Status      : " + (room.available ? "AVAILABLE" : "OCCUPIED"));

		System.out.println("------------------------------------------");
	}

	// =====================================================
	// DISPLAY BOOKING
	// =====================================================

	static void showBooking(Booking booking) {

		System.out.println("\n==========================================");

		System.out.println("Booking ID       : " + booking.id);

		System.out.println("Customer ID      : " + booking.userId);

		System.out.println("Customer         : " + booking.userName);

		System.out.println("Room Number      : " + booking.roomNumber);

		System.out.println("Room Type        : " + booking.roomType);

		System.out.println("AC Type          : " + booking.ac);

		System.out.println("Check-In         : " + booking.checkIn);

		System.out.println("Check-Out        : " + booking.checkOut);

		System.out.println("Guests           : " + booking.guests);

		System.out.println("Days             : " + booking.days);

		System.out.printf("Room Cost        : Rs. %.2f%n", booking.roomCost);

		System.out.printf("GST (5%%)         : Rs. %.2f%n", booking.gst);

		System.out.printf("Total            : Rs. %.2f%n", booking.total);

		System.out.println("Booking Status   : " + booking.status);

		System.out.println("Payment Status   : " + booking.paymentStatus);

		System.out.println("Payment Method   : " + booking.paymentMethod);

		System.out.println("Checked-In       : " + booking.checkedIn);

		System.out.println("Checked-Out      : " + booking.checkedOut);

		System.out.println("==========================================");
	}
}