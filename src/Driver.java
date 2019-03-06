public class Driver {
    private Car car;
    public Driver(Car car) {
        this.car = car;
    }

    public ParkingLotTicket parkCar(ParkingLot parkingLot) throws ParkingNotAvailablException {
        return parkingLot.getParkingSlot(car);
    }
}
