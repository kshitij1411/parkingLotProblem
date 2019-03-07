import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int numberOfSlots;
    private int availableSlots;
    private LotOwner owner;
    private AirportSecurity security;
    private Map<ParkingLotTicket,Car> ticketCarMapping;
    boolean currentlyOutOfCapacity;
    private int id;


    public ParkingLot(int numberOfSlots, LotOwner owner, AirportSecurity security, int id) {
        this.numberOfSlots = numberOfSlots;
        this.availableSlots = numberOfSlots;
        this.owner = owner;
        this.security = security;
        this.ticketCarMapping = new HashMap<>();
        this.currentlyOutOfCapacity = false;
        this.id = id;
    }

    public ParkingLotTicket parkTheCar(Car car, int lotId) throws ParkingNotAvailablException {
        if(areSlotsUnavailable())
            throw new ParkingNotAvailablException() ;
        car.assignSlot(availableSlots);
        ParkingLotTicket ticketNumber = new ParkingLotTicket(availableSlots,this.id);
        availableSlots--;
        this.availableSlots = availableSlots;
        if(areSlotsUnavailable()){
            owner.notifyLotIsFull();
            security.notifySecurity();
            currentlyOutOfCapacity = true;
        }
        ticketCarMapping.put(ticketNumber, car);
        return ticketNumber;
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

    public int getAvailableSlots() {
        return this.availableSlots;
    }

    public int getId() {
        return this.id;
    }

    public int getFilledSlots() {
        return this.numberOfSlots - this.availableSlots;
    }
}
