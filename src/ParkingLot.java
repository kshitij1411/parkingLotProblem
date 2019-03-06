public class ParkingLot {

    private int numberOfSlots;
    private int availableSlots;

    public ParkingLot(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
        this.availableSlots = numberOfSlots;
    }

    public int getParkingSlot(Car car) throws ParkingNotAvailablException {
        if(areSlotsUnavailable())
            throw new ParkingNotAvailablException() ;
        car.assignSlot(availableSlots);
        availableSlots--;
        return availableSlots+1;
    }

    private boolean areSlotsUnavailable() {
        return !(availableSlots > 0);
    }
}
