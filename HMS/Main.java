package HMS;

public class Main {

	public static void main(String[] args) {

		HotelSystem.initializeDatabase();

		// Load existing data
		HotelSystem.loadAllData();

		while (true) {
			System.out.println("\n==========================================");
			System.out.println("        HOTEL MANAGEMENT SYSTEM");
			System.out.println("==========================================");
			System.out.println("1. Admin Side");
			System.out.println("2. User Side");
			System.out.println("3. Exit");
			System.out.println("------------------------------------------");

			System.out.print("Enter your choice: ");
			int choice = HotelSystem.getInt();

			switch (choice) {

			case 1:
				AdminSide.start();
				break;

			case 2:
				UserSide.start();
				break;

			case 3:

				// Save all data before closing
				HotelSystem.saveAllData();

				System.out.println("\nAll data saved successfully.");
				System.out.println("Thank you for using Hotel Management System!");
				System.out.println("Application closed.");

				return;

			default:
				System.out.println("Invalid choice! Please enter 1-3.");
			}
		}
	}
}