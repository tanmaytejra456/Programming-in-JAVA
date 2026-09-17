public class Student extends Person {
    private String regNo;
    private String roomNo;
    private String messType;
    private double pendingFee;

    public Student(String regNo, String name, String phone, String roomNo, String messType, double pendingFee) {
        super(name, phone);
        this.regNo = regNo;
        this.roomNo = roomNo;
        this.messType = messType;
        this.pendingFee = pendingFee;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getMessType() {
        return messType;
    }

    public double getPendingFee() {
        return pendingFee;
    }

    public void setPendingFee(double pendingFee) {
        this.pendingFee = pendingFee;
    }

    public String toCsv() {
        return regNo + "," + name + "," + phone + "," + roomNo + "," + messType + "," + pendingFee;
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-15s | %-12s | %-6s | %-8s | Rs. %-8.2f |", 
                regNo, name, phone, roomNo, messType, pendingFee);
    }
}
