public class Driver {
    private Car car;
    public Driver(Car car) {
        this.car = car;
    }

    public int parkCar(ParkingLot parkingLot) throws ParkingNotAvailablException {
        return parkingLot.getParkingSlot();
    }
}
