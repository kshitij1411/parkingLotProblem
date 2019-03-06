import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int numberOfSlots;
    private int availableSlots;
    private LotOwner owner;
    private AirportSecurity security;
    private Map<ParkingLotTicket,Car> ticketCarMapping;
    boolean currentlyOutOfCapacity;


    public ParkingLot(int numberOfSlots, LotOwner owner, AirportSecurity security) {
        this.numberOfSlots = numberOfSlots;
        this.availableSlots = numberOfSlots;
        this.owner = owner;
        this.security = security;
        this.ticketCarMapping = new HashMap<>();
        this.currentlyOutOfCapacity = false;
    }

    public ParkingLotTicket getParkingSlot(Car car, LotOwner lotOwner) throws ParkingNotAvailablException {
        if(areSlotsUnavailable())
            throw new ParkingNotAvailablException() ;
        car.assignSlot(availableSlots);
        ParkingLotTicket number = new ParkingLotTicket(availableSlots);
        availableSlots--;
        if(areSlotsUnavailable()){
            owner.notifyLotIsFull();
            security.notifySecurity();
            currentlyOutOfCapacity = true;
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
        this.availableSlots++;
        if(this.availableSlots==1 && this.currentlyOutOfCapacity){
            owner.notifyLotHasSpace();
        }
        return car;
    }

}
