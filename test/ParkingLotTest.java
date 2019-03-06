import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {

    @Before
    public void setUp() {

    }

//    @Test
//    public void testIsSlotAvailableForCar() {
//        ParkingLot parkingLot = new ParkingLot(10);
//        Car car = new Car();
//        assertTrue(parkingLot.isSlotAvailableFor(car));
//    }

    @Test
    public void assignParkingSlot10ToCarWhen10NumberOfSlotAreAvailable() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();
        assertNotNull(parkingLot.getParkingSlot(car));
    }

    @Test(expected = ParkingNotAvailablException.class)
    public void getExceptionWhenParkingSlotIsNotAvailable() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        parkingLot.getParkingSlot(car);
    }

    @Test
    public void getDifferentParkingSlotWhenParkTwoCarsOneByOne() throws ParkingNotAvailablException {

        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car();
        Car secondCar  = new Car();
        assertNotNull(parkingLot.getParkingSlot(car));
        assertNotNull(parkingLot.getParkingSlot(secondCar));

    }

    @Test
    public void testUnparkTheCarGivenTheTicket() throws ParkingNotAvailablException, TicketNotFound {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car = new Car();
        ParkingLotTicket ticket = parkingLot.getParkingSlot(car);

        Car carFromTicket = parkingLot.unParkCarGivenTicket(ticket);

        assertEquals(car,carFromTicket);

    }

    @Test
    public void testUnParking2Cars() throws TicketNotFound, ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(5);
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingLotTicket ticket1 = parkingLot.getParkingSlot(car1);
        ParkingLotTicket ticket2 = parkingLot.getParkingSlot(car2);
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);

        assertEquals(car1,carFromTicket1);
        Car carFromTicket2 = parkingLot.unParkCarGivenTicket(ticket2);

        assertEquals(car2,carFromTicket2);

    }

    
}