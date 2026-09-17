public class Room {
    private String roomNo;
    private String type; // AC, Non-AC
    private int capacity;
    private int currentOccupants;

    public Room(String roomNo, String type, int capacity, int currentOccupants) {
        this.roomNo = roomNo;
        this.type = type;
        this.capacity = capacity;
        this.currentOccupants = currentOccupants;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupants() {
        return currentOccupants;
    }

    public boolean isFull() {
        return currentOccupants >= capacity;
    }

    public void addOccupant() {
        this.currentOccupants++;
    }

    public void removeOccupant() {
        if (this.currentOccupants > 0) {
            this.currentOccupants--;
        }
    }
}
