public class ParkingLotTicket {
    private int ticketNumber;
    private int parkingLotId;

    public ParkingLotTicket(int ticketNumber, int parkingLotId) {
        this.ticketNumber = ticketNumber;
        this.parkingLotId = parkingLotId;
    }

    public int getParkingLotId() {
        return this.parkingLotId;
    }
}
