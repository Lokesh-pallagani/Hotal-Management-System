package HMS;

class AdminSide {

	static final String ADMIN_ID = "admin";

	static final String ADMIN_PASSWORD = "admin123";

	// =====================================================
	// ADMIN LOGIN
	// =====================================================

	static void start() {

		System.out.println("\n==========================================");

		System.out.println("              ADMIN LOGIN");

		System.out.println("==========================================");

		System.out.print("Admin ID: ");

		String id = HotelSystem.sc.nextLine();

		System.out.print("Password: ");

		String password = HotelSystem.sc.nextLine();

		if (!ADMIN_ID.equals(id) || !ADMIN_PASSWORD.equals(password)) {

			System.out.println("\nInvalid Admin ID or Password!");

			return;
		}

		System.out.println("\nAdmin Login Successful!");

		dashboard();
	}

	// =====================================================
	// DASHBOARD
	// =====================================================

	static void dashboard() {

		while (true) {

			System.out.println("\n==========================================");

			System.out.println("             ADMIN DASHBOARD");

			System.out.println("==========================================");

			System.out.println("1. Customer Management");

			System.out.println("2. Employee Management");

			System.out.println("3. Room Management");

			System.out.println("4. Booking Management");

			System.out.println("5. Payment Management");

			System.out.println("6. Check-In Management");

			System.out.println("7. Check-Out Management");

			System.out.println("8. Bill Management");

			System.out.println("9. Reports");

			System.out.println("10. Logout");

			System.out.print("\nEnter choice: ");

			int choice = HotelSystem.getInt();

			switch (choice) {

			case 1:
				customerManagement();
				break;

			case 2:
				employeeManagement();
				break;

			case 3:
				roomManagement();
				break;

			case 4:
				bookingManagement();
				break;

			case 5:
				paymentManagement();
				break;

			case 6:
				checkInManagement();
				break;

			case 7:
				checkOutManagement();
				break;

			case 8:
				billManagement();
				break;

			case 9:
				reports();
				break;

			case 10:

				return;

			default:

				System.out.println("Invalid choice!");
			}
		}
	}

	// =====================================================
	// CUSTOMER MANAGEMENT
	// =====================================================

	static void customerManagement() {

		while (true) {

			System.out.println("\n========== CUSTOMER MANAGEMENT ==========");

			System.out.println("1. Add Customer");

			System.out.println("2. Search Customer");

			System.out.println("3. Update Customer");

			System.out.println("4. Delete Customer");

			System.out.println("5. View All Customers");

			System.out.println("6. Back");

			System.out.print("Enter choice: ");

			int choice = HotelSystem.getInt();

			if (choice == 6)
				return;

			switch (choice) {

			case 1:
				addCustomer();
				break;

			case 2:
				searchCustomer();
				break;

			case 3:
				updateCustomer();
				break;

			case 4:
				deleteCustomer();
				break;

			case 5:
				viewCustomers();
				break;

			default:
				System.out.println("Invalid choice!");
			}
		}
	}

	// =====================================================
	// ADD CUSTOMER
	// =====================================================

	static void addCustomer() {

		String id = HotelSystem.nextUserId();

		System.out.println("\nGenerated Customer ID: " + id);

		System.out.print("Name: ");

		String name = HotelSystem.sc.nextLine();

		System.out.print("Mobile: ");

		String mobile = HotelSystem.sc.nextLine();

		if (!mobile.matches("\\d{10}")) {

			System.out.println("Invalid mobile number!");

			return;
		}

		System.out.print("Aadhaar: ");

		String aadhaar = HotelSystem.sc.nextLine();

		if (!aadhaar.matches("\\d{12}")) {

			System.out.println("Invalid Aadhaar number!");

			return;
		}

		System.out.print("Gender: ");

		String gender = HotelSystem.sc.nextLine();

		System.out.print("City: ");

		String city = HotelSystem.sc.nextLine();

		System.out.print("Password: ");

		String password = HotelSystem.sc.nextLine();

		HotelSystem.users.add(new HotelSystem.User(id, name, mobile, aadhaar, gender, city, password));

		HotelSystem.saveUsers();

		System.out.println("Customer added successfully!");
	}

	// =====================================================
	// SEARCH CUSTOMER
	// =====================================================

	static void searchCustomer() {

		System.out.print("\nEnter Customer ID: ");

		String id = HotelSystem.sc.nextLine();

		HotelSystem.User user = HotelSystem.findUser(id);

		if (user == null) {

			System.out.println("Customer not found!");

			return;
		}

		printCustomer(user);
	}

	// =====================================================
	// UPDATE CUSTOMER
	// =====================================================

	static void updateCustomer() {

		System.out.print("\nEnter Customer ID: ");

		String id = HotelSystem.sc.nextLine();

		HotelSystem.User user = HotelSystem.findUser(id);

		if (user == null) {

			System.out.println("Customer not found!");

			return;
		}

		System.out.print("New Name: ");

		user.name = HotelSystem.sc.nextLine();

		System.out.print("New Mobile: ");

		String mobile = HotelSystem.sc.nextLine();

		if (!mobile.matches("\\d{10}")) {

			System.out.println("Invalid mobile number!");

			return;
		}

		user.mobile = mobile;

		System.out.print("New City: ");

		user.city = HotelSystem.sc.nextLine();

		HotelSystem.saveUsers();

		System.out.println("Customer updated successfully!");
	}

	// =====================================================
	// DELETE CUSTOMER
	// =====================================================

	static void deleteCustomer() {

		System.out.print("\nEnter Customer ID: ");

		String id = HotelSystem.sc.nextLine();

		HotelSystem.User user = HotelSystem.findUser(id);

		if (user == null) {

			System.out.println("Customer not found!");

			return;
		}

		HotelSystem.users.remove(user);

		HotelSystem.saveUsers();

		System.out.println("Customer deleted successfully!");
	}

	// =====================================================
	// VIEW CUSTOMERS
	// =====================================================

	static void viewCustomers() {

		System.out.println("\n========== ALL CUSTOMERS ==========");

		for (HotelSystem.User user : HotelSystem.users) {

			printCustomer(user);
		}
	}

	static void printCustomer(HotelSystem.User user) {

		System.out.println("\n------------------------------------------");

		System.out.println("Customer ID : " + user.id);

		System.out.println("Name        : " + user.name);

		System.out.println("Mobile      : " + user.mobile);

		System.out.println("Aadhaar     : " + user.aadhaar);

		System.out.println("Gender      : " + user.gender);

		System.out.println("City        : " + user.city);
	}

	// =====================================================
	// EMPLOYEE MANAGEMENT
	// =====================================================

	static void employeeManagement() {

		while (true) {

			System.out.println("\n========== EMPLOYEE MANAGEMENT ==========");

			System.out.println("1. Add Employee");

			System.out.println("2. Search Employee");

			System.out.println("3. Update Employee");

			System.out.println("4. Delete Employee");

			System.out.println("5. View All Employees");

			System.out.println("6. Back");

			System.out.print("Enter choice: ");

			int choice = HotelSystem.getInt();

			if (choice == 6)
				return;

			switch (choice) {

			case 1:
				addEmployee();
				break;

			case 2:
				searchEmployee();
				break;

			case 3:
				updateEmployee();
				break;

			case 4:
				deleteEmployee();
				break;

			case 5:
				viewEmployees();
				break;

			default:
				System.out.println("Invalid choice!");
			}
		}
	}

	// =====================================================
	// ADD EMPLOYEE
	// =====================================================

	static void addEmployee() {

		String id = HotelSystem.nextEmployeeId();

		System.out.println("\nEmployee ID: " + id);

		System.out.print("Name: ");

		String name = HotelSystem.sc.nextLine();

		System.out.print("Role: ");

		String role = HotelSystem.sc.nextLine();

		System.out.print("Mobile: ");

		String mobile = HotelSystem.sc.nextLine();

		if (!mobile.matches("\\d{10}")) {

			System.out.println("Invalid mobile number!");

			return;
		}

		System.out.print("Salary: ");

		double salary = HotelSystem.getDouble();

		if (salary <= 0) {

			System.out.println("Invalid salary!");

			return;
		}

		HotelSystem.employees.add(new HotelSystem.Employee(id, name, role, mobile, salary));

		HotelSystem.saveEmployees();

		System.out.println("Employee added successfully!");
	}

	// =====================================================
	// SEARCH EMPLOYEE
	// =====================================================

	static void searchEmployee() {

		System.out.print("\nEnter Employee ID: ");

		String id = HotelSystem.sc.nextLine();

		HotelSystem.Employee employee = HotelSystem.findEmployee(id);

		if (employee == null) {

			System.out.println("Employee not found!");

			return;
		}

		printEmployee(employee);
	}

	// =====================================================
	// UPDATE EMPLOYEE
	// =====================================================

	static void updateEmployee() {

		System.out.print("\nEnter Employee ID: ");

		String id = HotelSystem.sc.nextLine();

		HotelSystem.Employee employee = HotelSystem.findEmployee(id);

		if (employee == null) {

			System.out.println("Employee not found!");

			return;
		}

		System.out.print("New Role: ");

		employee.role = HotelSystem.sc.nextLine();

		System.out.print("New Salary: ");

		double salary = HotelSystem.getDouble();

		if (salary <= 0) {

			System.out.println("Invalid salary!");

			return;
		}

		employee.salary = salary;

		HotelSystem.saveEmployees();

		System.out.println("Employee updated successfully!");
	}

	// =====================================================
	// DELETE EMPLOYEE
	// =====================================================

	static void deleteEmployee() {

		System.out.print("\nEnter Employee ID: ");

		String id = HotelSystem.sc.nextLine();

		HotelSystem.Employee employee = HotelSystem.findEmployee(id);

		if (employee == null) {

			System.out.println("Employee not found!");

			return;
		}

		HotelSystem.employees.remove(employee);

		HotelSystem.saveEmployees();

		System.out.println("Employee deleted successfully!");
	}

	// =====================================================
	// VIEW EMPLOYEES
	// =====================================================

	static void viewEmployees() {

		System.out.println("\n========== ALL EMPLOYEES ==========");

		for (HotelSystem.Employee employee : HotelSystem.employees) {

			printEmployee(employee);
		}
	}

	static void printEmployee(HotelSystem.Employee employee) {

		System.out.println("\n------------------------------------------");

		System.out.println("Employee ID : " + employee.id);

		System.out.println("Name        : " + employee.name);

		System.out.println("Role        : " + employee.role);

		System.out.println("Mobile      : " + employee.mobile);

		System.out.printf("Salary      : Rs. %.2f%n", employee.salary);
	}

	// =====================================================
	// ROOM MANAGEMENT
	// =====================================================

	static void roomManagement() {

		while (true) {

			System.out.println("\n========== ROOM MANAGEMENT ==========");

			System.out.println("1. Add Room");

			System.out.println("2. Search Room");

			System.out.println("3. Update Room");

			System.out.println("4. Delete Room");

			System.out.println("5. View All Rooms");

			System.out.println("6. View Available Rooms");

			System.out.println("7. Back");

			System.out.print("Enter choice: ");

			int choice = HotelSystem.getInt();

			if (choice == 7)
				return;

			switch (choice) {

			case 1:
				addRoom();
				break;

			case 2:
				searchRoom();
				break;

			case 3:
				updateRoom();
				break;

			case 4:
				deleteRoom();
				break;

			case 5:
				viewRooms();
				break;

			case 6:
				viewAvailableRooms();
				break;

			default:
				System.out.println("Invalid choice!");
			}
		}
	}

	// =====================================================
	// ADD ROOM
	// =====================================================

	static void addRoom() {

		System.out.print("\nRoom Number: ");

		String number = HotelSystem.sc.nextLine();

		if (HotelSystem.findRoom(number) != null) {

			System.out.println("Room already exists!");

			return;
		}

		System.out.print("Room Type: ");

		String type = HotelSystem.sc.nextLine();

		System.out.print("AC / NON-AC: ");

		String ac = HotelSystem.sc.nextLine();

		System.out.print("Capacity: ");

		int capacity = HotelSystem.getInt();

		System.out.print("Price Per Day: ");

		double price = HotelSystem.getDouble();

		if (capacity <= 0 || price <= 0) {

			System.out.println("Invalid room details!");

			return;
		}

		HotelSystem.rooms.add(new HotelSystem.Room(number, type, ac, capacity, price));

		HotelSystem.saveRooms();

		System.out.println("Room added successfully!");
	}

	// =====================================================
	// SEARCH ROOM
	// =====================================================

	static void searchRoom() {

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
	// UPDATE ROOM
	// =====================================================

	static void updateRoom() {

		System.out.print("\nEnter Room Number: ");

		String number = HotelSystem.sc.nextLine();

		HotelSystem.Room room = HotelSystem.findRoom(number);

		if (room == null) {

			System.out.println("Room not found!");

			return;
		}

		System.out.print("New Room Type: ");

		room.type = HotelSystem.sc.nextLine();

		System.out.print("New AC Type: ");

		room.ac = HotelSystem.sc.nextLine();

		System.out.print("New Capacity: ");

		room.capacity = HotelSystem.getInt();

		System.out.print("New Price Per Day: ");

		room.price = HotelSystem.getDouble();

		HotelSystem.saveRooms();

		System.out.println("Room updated successfully!");
	}

	// =====================================================
	// DELETE ROOM
	// =====================================================

	static void deleteRoom() {

		System.out.print("\nEnter Room Number: ");

		String number = HotelSystem.sc.nextLine();

		HotelSystem.Room room = HotelSystem.findRoom(number);

		if (room == null) {

			System.out.println("Room not found!");

			return;
		}

		if (!room.available) {

			System.out.println("Occupied room cannot be deleted!");

			return;
		}

		HotelSystem.rooms.remove(room);

		HotelSystem.saveRooms();

		System.out.println("Room deleted successfully!");
	}

	// =====================================================
	// VIEW ROOMS
	// =====================================================

	static void viewRooms() {

		System.out.println("\n========== ALL ROOMS ==========");

		for (HotelSystem.Room room : HotelSystem.rooms) {

			HotelSystem.showRoom(room);
		}
	}

	// =====================================================
	// AVAILABLE ROOMS
	// =====================================================

	static void viewAvailableRooms() {

		System.out.println("\n========== AVAILABLE ROOMS ==========");

		boolean found = false;

		for (HotelSystem.Room room : HotelSystem.rooms) {

			if (room.available) {

				HotelSystem.showRoom(room);

				found = true;
			}
		}

		if (!found) {

			System.out.println("No rooms available.");
		}
	}

	// =====================================================
	// BOOKING MANAGEMENT
	// =====================================================

	static void bookingManagement() {

		System.out.println("\n========== BOOKING MANAGEMENT ==========");

		if (HotelSystem.bookings.isEmpty()) {

			System.out.println("No bookings found.");

			return;
		}

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			HotelSystem.showBooking(booking);
		}
	}

	// =====================================================
	// PAYMENT MANAGEMENT
	// =====================================================

	static void paymentManagement() {

		System.out.println("\n========== PAYMENT MANAGEMENT ==========");

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			System.out.println("\nBooking ID : " + booking.id);

			System.out.println("Customer   : " + booking.userName);

			System.out.printf("Amount     : Rs. %.2f%n", booking.total);

			System.out.println("Method     : " + booking.paymentMethod);

			System.out.println("Status     : " + booking.paymentStatus);
		}
	}

	// =====================================================
	// CHECK-IN MANAGEMENT
	// =====================================================

	static void checkInManagement() {

		System.out.println("\n========== ACTIVE CHECK-INS ==========");

		boolean found = false;

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			if (booking.checkedIn && !booking.checkedOut) {

				System.out.println("Booking : " + booking.id + " | Customer : " + booking.userName + " | Room : "
						+ booking.roomNumber);

				found = true;
			}
		}

		if (!found) {

			System.out.println("No active check-ins.");
		}
	}

	// =====================================================
	// CHECK-OUT MANAGEMENT
	// =====================================================

	static void checkOutManagement() {

		System.out.println("\n========== COMPLETED CHECK-OUTS ==========");

		boolean found = false;

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			if (booking.checkedOut) {

				System.out.println("Booking : " + booking.id + " | Customer : " + booking.userName + " | Room : "
						+ booking.roomNumber);

				found = true;
			}
		}

		if (!found) {

			System.out.println("No completed check-outs.");
		}
	}

	// =====================================================
	// BILL MANAGEMENT
	// =====================================================

	static void billManagement() {

		System.out.println("\n========== BILL MANAGEMENT ==========");

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			HotelSystem.showBooking(booking);
		}
	}

	// =====================================================
	// REPORTS
	// =====================================================

	static void reports() {

		int available = 0;
		int occupied = 0;

		int confirmed = 0;
		int cancelled = 0;

		double revenue = 0;

		for (HotelSystem.Room room : HotelSystem.rooms) {

			if (room.available)
				available++;

			else
				occupied++;
		}

		for (HotelSystem.Booking booking : HotelSystem.bookings) {

			if (booking.status.equalsIgnoreCase("CANCELLED")) {

				cancelled++;

			} else {

				confirmed++;

				revenue += booking.total;
			}
		}

		System.out.println("\n==========================================");

		System.out.println("             HOTEL REPORT");

		System.out.println("==========================================");

		System.out.println("Total Customers : " + HotelSystem.users.size());

		System.out.println("Total Employees : " + HotelSystem.employees.size());

		System.out.println("Total Rooms     : " + HotelSystem.rooms.size());

		System.out.println("Available Rooms : " + available);

		System.out.println("Occupied Rooms  : " + occupied);

		System.out.println("Total Bookings  : " + HotelSystem.bookings.size());

		System.out.println("Confirmed       : " + confirmed);

		System.out.println("Cancelled       : " + cancelled);

		System.out.printf("Total Revenue   : Rs. %.2f%n", revenue);

		System.out.println("==========================================");
	}
}