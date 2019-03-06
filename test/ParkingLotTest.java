import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class ParkingLotTest {

    LotOwner lotOwner;
    AirportSecurity security;

    @Before
    public void setUp() {

        lotOwner = Mockito.mock(LotOwner.class);
        security = Mockito.mock(AirportSecurity.class);
        when(security.notifySecurity()).thenReturn(true);
    }

    @Test
    public void assignParkingSlot10ToCarWhen10NumberOfSlotAreAvailable() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(10, lotOwner, security);
        Car car = new Car();
        assertNotNull(parkingLot.getParkingSlot(car, lotOwner));
    }

    @Test(expected = ParkingNotAvailablException.class)
    public void getExceptionWhenParkingSlotIsNotAvailable() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(0, lotOwner, security);
        Car car = new Car();
        parkingLot.getParkingSlot(car, lotOwner);
    }

    @Test
    public void getDifferentParkingSlotWhenParkTwoCarsOneByOne() throws ParkingNotAvailablException {

        ParkingLot parkingLot = new ParkingLot(5, lotOwner, security);
        Car car = new Car();
        Car secondCar  = new Car();
        assertNotNull(parkingLot.getParkingSlot(car, lotOwner));
        assertNotNull(parkingLot.getParkingSlot(secondCar, lotOwner));

    }

    @Test
    public void testUnparkTheCarGivenTheTicket() throws ParkingNotAvailablException, TicketNotFound {
        ParkingLot parkingLot = new ParkingLot(5, lotOwner, security);
        Car car = new Car();
        ParkingLotTicket ticket = parkingLot.getParkingSlot(car, lotOwner);

        Car carFromTicket = parkingLot.unParkCarGivenTicket(ticket);

        assertEquals(car,carFromTicket);

    }

    @Test
    public void testUnParking2Cars() throws TicketNotFound, ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(5, lotOwner, security);
        Car car1 = new Car();
        Car car2 = new Car();

        ParkingLotTicket ticket1 = parkingLot.getParkingSlot(car1, lotOwner);
        ParkingLotTicket ticket2 = parkingLot.getParkingSlot(car2, lotOwner);
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);

        assertEquals(car1,carFromTicket1);
        Car carFromTicket2 = parkingLot.unParkCarGivenTicket(ticket2);

        assertEquals(car2,carFromTicket2);

    }

    @Test
    public void notifyLotOwnerIfParkingLotIsFull() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(1, lotOwner, security);
        Car car1 = new Car();
        ParkingLotTicket ticket1 = parkingLot.getParkingSlot(car1, lotOwner);
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
    }

    @Test
    public void doNotNotifyLotOwnerIfParkingLotIsNotFullAndHaveOneSlot() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(2, lotOwner, security);
        Car car1 = new Car();
        ParkingLotTicket ticket1 = parkingLot.getParkingSlot(car1, lotOwner);
        Mockito.verify(lotOwner,times(0)).notifyLotIsFull();
    }

    @Test(expected = ParkingNotAvailablException.class)
    public void doNotNotifyAgainLotOwnerIfParkingLotIsFull() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(1, lotOwner, security);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLotTicket ticket1 = parkingLot.getParkingSlot(car1, lotOwner);
        ParkingLotTicket ticket2 = parkingLot.getParkingSlot(car2, lotOwner);
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
    }

    @Test
    public void notifyLotOwnerParkingLotHasSpaceAgain() throws TicketNotFound, ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(1, lotOwner, security);
        Car car1 = new Car();
        ParkingLotTicket ticket1 = parkingLot.getParkingSlot(car1, lotOwner);
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);
        Mockito.verify(lotOwner,times(1)).notifyLotHasSpace();
    }

    @Test
    public void doNotNOtifyOnEveryUnpark() throws ParkingNotAvailablException, TicketNotFound {
        ParkingLot parkingLot = new ParkingLot(2, lotOwner, security);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLotTicket ticket1 = parkingLot.getParkingSlot(car1, lotOwner);
        ParkingLotTicket ticket2 = parkingLot.getParkingSlot(car2, lotOwner);
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);
        Car carFromTicket2 = parkingLot.unParkCarGivenTicket(ticket2);
        Mockito.verify(lotOwner,times(1)).notifyLotHasSpace();
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
    }

    @Test
    public void notifyAirportSecurityWhenParkingLotIsFull() throws ParkingNotAvailablException, TicketNotFound {
        ParkingLot parkingLot = new ParkingLot(2, lotOwner, security);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLotTicket ticket1 = parkingLot.getParkingSlot(car1, lotOwner);
        ParkingLotTicket ticket2 = parkingLot.getParkingSlot(car2, lotOwner);
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);
        Car carFromTicket2 = parkingLot.unParkCarGivenTicket(ticket2);
        Mockito.verify(lotOwner,times(1)).notifyLotHasSpace();
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
        Mockito.verify(security,times(1)).notifySecurity();
    }
}