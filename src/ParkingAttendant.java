public class ParkingAttendant {
    private ParkingLot parkingLot;

    public ParkingAttendant(ParkingLot parkingLot) {

        this.parkingLot = parkingLot;
    }

    public ParkingLotTicket park(Car car) throws ParkingNotAvailablException {
        return parkingLot.parkTheCar(car);
    }
}
