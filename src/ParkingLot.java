import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int numberOfSlots;
    private int availableSlots;
    private LotOwner owner;
    private Map<ParkingLotTicket,Car> ticketCarMapping;

    public ParkingLot(int numberOfSlots, LotOwner owner) {
        this.numberOfSlots = numberOfSlots;
        this.availableSlots = numberOfSlots;
        this.owner = owner;
        this.ticketCarMapping = new HashMap<>();
    }

    public ParkingLotTicket getParkingSlot(Car car, LotOwner lotOwner) throws ParkingNotAvailablException {
        if(areSlotsUnavailable())
            throw new ParkingNotAvailablException() ;
        car.assignSlot(availableSlots);
        ParkingLotTicket number = new ParkingLotTicket(availableSlots);
        availableSlots--;
        if(areSlotsUnavailable()){
            owner.notifyLotIsFull();
        }
        ticketCarMapping.put(number, car);
        return number;
    }

    private boolean areSlotsUnavailable() {
        return !(availableSlots > 0);
    }

    public Car unParkCarGivenTicket(ParkingLotTicket ticket) throws TicketNotFound {
        if(!this.ticketCarMapping.containsKey(ticket))
            throw new TicketNotFound();
        Car car = this.ticketCarMapping.get(ticket);
        this.ticketCarMapping.remove(ticket);
        return car;
    }

}
