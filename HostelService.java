import java.io.*;
import java.util.ArrayList;

public class HostelService {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private final String DATA_FILE = "hostel_data.csv";

    public HostelService() {
        initDefaultRooms();
        loadDataFromFile();
    }

    private void initDefaultRooms() {
        rooms.add(new Room("101", "Non-AC", 2, 0));
        rooms.add(new Room("102", "Non-AC", 2, 0));
        rooms.add(new Room("201", "AC", 2, 0));
        rooms.add(new Room("202", "AC", 1, 0));
    }

    public Room findRoom(String roomNo) {
        for (Room r : rooms) {
            if (r.getRoomNo().equalsIgnoreCase(roomNo)) {
                return r;
            }
        }
        return null;
    }

    public Student findStudent(String regNo) {
        for (Student s : students) {
            if (s.getRegNo().equalsIgnoreCase(regNo)) {
                return s;
            }
        }
        return null;
    }

    public void allotRoom(String regNo, String name, String phone, String roomNo, String messType) 
            throws RoomFullException, IllegalArgumentException {
        
        if (findStudent(regNo) != null) {
            throw new IllegalArgumentException("Student with Reg No " + regNo + " is already registered!");
        }

        Room room = findRoom(roomNo);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNo + " does not exist.");
        }

        if (room.isFull()) {
            throw new RoomFullException("Room " + roomNo + " is already full to capacity (" + room.getCapacity() + ").");
        }

        double initialFee = calculateBaseFee(room.getType(), messType);

        Student newStudent = new Student(regNo, name, phone, roomNo, messType, initialFee);
        students.add(newStudent);
        room.addOccupant();
        saveDataToFile();
    }

    public boolean vacateStudent(String regNo) {
        Student s = findStudent(regNo);
        if (s != null) {
            Room r = findRoom(s.getRoomNo());
            if (r != null) {
                r.removeOccupant();
            }
            students.remove(s);
            saveDataToFile();
            return true;
        }
        return false;
    }

    public double calculateBaseFee(String roomType, String messType) {
        double roomCharge = roomType.equalsIgnoreCase("AC") ? 45000.0 : 30000.0;
        double messCharge = messType.equalsIgnoreCase("Non-Veg") ? 22000.0 : 18000.0;
        return roomCharge + messCharge;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students currently allotted in the hostel.");
            return;
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println(String.format("| %-10s | %-15s | %-12s | %-6s | %-8s | %-12s |", 
                "Reg No", "Name", "Phone", "Room", "Mess", "Fee Due"));
        System.out.println("----------------------------------------------------------------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    public void displayRoomStatus() {
        System.out.println("-------------------------------------------------------");
        System.out.println(String.format("| %-8s | %-8s | %-10s | %-10s |", "Room No", "Type", "Capacity", "Occupied"));
        System.out.println("-------------------------------------------------------");
        for (Room r : rooms) {
            System.out.println(String.format("| %-8s | %-8s | %-10d | %-10d |", 
                    r.getRoomNo(), r.getType(), r.getCapacity(), r.getCurrentOccupants()));
        }
        System.out.println("-------------------------------------------------------");
    }

    private void saveDataToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Student s : students) {
                bw.write(s.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not save data to file: " + e.getMessage());
        }
    }

    private void loadDataFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String regNo = parts[0];
                    String name = parts[1];
                    String phone = parts[2];
                    String roomNo = parts[3];
                    String messType = parts[4];
                    double fee = Double.parseDouble(parts[5]);

                    Student s = new Student(regNo, name, phone, roomNo, messType, fee);
                    students.add(s);

                    Room r = findRoom(roomNo);
                    if (r != null) {
                        r.addOccupant();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Notice: Fresh setup initialized.");
        }
    }
}
