import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HostelService service = new HostelService();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("==================================================");
        System.out.println("       HOSTEL ROOM & BILLING SYSTEM (v1.0)       ");
        System.out.println("==================================================");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Allot New Room to Student");
            System.out.println("2. Display All Allotted Students");
            System.out.println("3. Check Room Availability & Status");
            System.out.println("4. Vacate Student from Room");
            System.out.println("5. Exit System");
            System.out.print("Enter choice (1-5): ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Enter Reg No: ");
                        String reg = sc.nextLine().trim();

                        System.out.print("Enter Student Name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter Phone: ");
                        String phone = sc.nextLine().trim();

                        System.out.print("Enter Room No to Allot (101, 102, 201, 202): ");
                        String room = sc.nextLine().trim();

                        System.out.print("Enter Mess Type (Veg/Non-Veg): ");
                        String mess = sc.nextLine().trim();

                        service.allotRoom(reg, name, phone, room, mess);
                        System.out.println(">> Success: Room allotted and record saved.");
                    } catch (RoomFullException | IllegalArgumentException e) {
                        System.out.println(">> Error: " + e.getMessage());
                    }
                    break;

                case "2":
                    service.displayAllStudents();
                    break;

                case "3":
                    service.displayRoomStatus();
                    break;

                case "4":
                    System.out.print("Enter Reg No to vacate: ");
                    String reg = sc.nextLine().trim();
                    if (service.vacateStudent(reg)) {
                        System.out.println(">> Success: Student vacated and room freed.");
                    } else {
                        System.out.println(">> Error: Student record not found.");
                    }
                    break;

                case "5":
                    System.out.println("Thank you! Exiting application.");
                    running = false;
                    break;

                default:
                    System.out.println(">> Invalid choice! Please select between 1 and 5.");
            }
        }
        sc.close();
    }
}
