import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;

public class ParkingLotTest {

    private LotOwner lotOwner;
    private AirportSecurity security;

    @Before
    public void setUp() {

        lotOwner = Mockito.mock(LotOwner.class);
        security = Mockito.mock(AirportSecurity.class);
        //when(security.notifySecurity()).thenReturn(true);
    }

    @Test
    public void assignACarNumberToCar() {
        Car car = new Car("ABC1234");
        assertEquals("ABC1234",car.getCarNumber());
    }

    @Test
    public void assignParkingSlot10ToCarWhen10NumberOfSlotAreAvailable() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(10, lotOwner, security, 0);
        Car car = new Car("jhdcvhjb");
        assertNotNull(parkingLot.parkTheCar(car, 0));
    }

    @Test(expected = ParkingNotAvailablException.class)
    public void getExceptionWhenParkingSlotIsNotAvailable() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(0, lotOwner, security, 0);
        Car car = new Car("jhcv");
        parkingLot.parkTheCar(car, 0);
    }

    @Test
    public void getDifferentParkingSlotWhenParkTwoCarsOneByOne() throws ParkingNotAvailablException {

        ParkingLot parkingLot = new ParkingLot(5, lotOwner, security, 0);
        Car car = new Car("jhcbvs");
        Car secondCar  = new Car("hcbsjk");
        assertNotNull(parkingLot.parkTheCar(car, 0));
        assertNotNull(parkingLot.parkTheCar(secondCar, 0));

    }

    @Test
    public void testUnparkTheCarGivenTheTicket() throws ParkingNotAvailablException, TicketNotFound {
        ParkingLot parkingLot = new ParkingLot(5, lotOwner, security, 0);
        Car car = new Car("chb");
        ParkingLotTicket ticket = parkingLot.parkTheCar(car, 0);

        Car carFromTicket = parkingLot.unParkCarGivenTicket(ticket);

        assertEquals(car,carFromTicket);

    }

    @Test
    public void testUnParking2Cars() throws TicketNotFound, ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(5, lotOwner, security, 0);
        Car car1 = new Car("jhvcbj");
        Car car2 = new Car("cjhb");

        ParkingLotTicket ticket1 = parkingLot.parkTheCar(car1, 0);
        ParkingLotTicket ticket2 = parkingLot.parkTheCar(car2, 0);
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);

        assertEquals(car1,carFromTicket1);
        Car carFromTicket2 = parkingLot.unParkCarGivenTicket(ticket2);

        assertEquals(car2,carFromTicket2);

    }

    @Test
    public void notifyLotOwnerIfParkingLotIsFull() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(1, lotOwner, security, 0);
        Car car1 = new Car("cjhfeb");
        ParkingLotTicket ticket1 = parkingLot.parkTheCar(car1, 0);
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
    }

    @Test
    public void doNotNotifyLotOwnerIfParkingLotIsNotFullAndHaveOneSlot() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(2, lotOwner, security, 0);
        Car car1 = new Car("cjhb");
        ParkingLotTicket ticket1 = parkingLot.parkTheCar(car1, 0);
        Mockito.verify(lotOwner,times(0)).notifyLotIsFull();
    }

    @Test(expected = ParkingNotAvailablException.class)
    public void doNotNotifyAgainLotOwnerIfParkingLotIsFull() throws ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(1, lotOwner, security, 0);
        Car car1 = new Car("hvbk");
        Car car2 = new Car("cvjhe");
        ParkingLotTicket ticket1 = parkingLot.parkTheCar(car1, 0);
        ParkingLotTicket ticket2 = parkingLot.parkTheCar(car2, 0);
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
    }

    @Test
    public void notifyLotOwnerParkingLotHasSpaceAgain() throws TicketNotFound, ParkingNotAvailablException {
        ParkingLot parkingLot = new ParkingLot(1, lotOwner, security, 0);
        Car car1 = new Car("cjhbf");
        ParkingLotTicket ticket1 = parkingLot.parkTheCar(car1, 0);
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);
        Mockito.verify(lotOwner,times(1)).notifyLotHasSpace();
    }

    @Test
    public void doNotNOtifyOnEveryUnpark() throws ParkingNotAvailablException, TicketNotFound {
        ParkingLot parkingLot = new ParkingLot(2, lotOwner, security, 0);
        Car car1 = new Car("jhcbv");
        Car car2 = new Car("cjhbv");
        ParkingLotTicket ticket1 = parkingLot.parkTheCar(car1, 0);
        ParkingLotTicket ticket2 = parkingLot.parkTheCar(car2, 0);
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);
        Car carFromTicket2 = parkingLot.unParkCarGivenTicket(ticket2);
        Mockito.verify(lotOwner,times(1)).notifyLotHasSpace();
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
    }

    @Test
    public void notifyAirportSecurityWhenParkingLotIsFull() throws ParkingNotAvailablException, TicketNotFound {
        ParkingLot parkingLot = new ParkingLot(2, lotOwner, security, 0);
        Car car1 = new Car("shjcvhd");
        Car car2 = new Car("cjhdsbc");
        ParkingLotTicket ticket1 = parkingLot.parkTheCar(car1, 0);
        ParkingLotTicket ticket2 = parkingLot.parkTheCar(car2, 0);
        Car carFromTicket1 = parkingLot.unParkCarGivenTicket(ticket1);
        Car carFromTicket2 = parkingLot.unParkCarGivenTicket(ticket2);
        Mockito.verify(lotOwner,times(1)).notifyLotHasSpace();
        Mockito.verify(lotOwner,times(1)).notifyLotIsFull();
        Mockito.verify(security,times(1)).notifySecurity();
    }

    @Test
    public void ensureTicketHasParkingLotId() throws ParkingNotAvailablException {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2, lotOwner, security, 0));
        parkingLotList.add(new ParkingLot(2, lotOwner, security, 1));

        Car car1 = new Car("vhjbdfh");
        Car car2= new Car("vhjbdfhdhcbf");

        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLotList);

        ParkingLotTicket ticket1 = parkingAttendant.park(car1);
        ParkingLotTicket ticket2 = parkingAttendant.park(car2);

        assertEquals(1,ticket2.getParkingLotId());
    }

    @Test
    public void ensureCarsAreParkedEvenlyAmongLots() throws ParkingNotAvailablException, TicketNotFound {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(5, lotOwner, security, 0));
        parkingLotList.add(new ParkingLot(5, lotOwner, security, 1));
        parkingLotList.add(new ParkingLot(5, lotOwner, security, 2));
        parkingLotList.add(new ParkingLot(5, lotOwner, security, 3));

        Car car1 = new Car("vhjbdfh");
        Car car2 = new Car("vhjbdcswcfh");
        Car car3 = new Car("vhjbdcsjnfh");
        Car car4 = new Car("vhjhcsbjbdfh");

        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLotList);

        ParkingLotTicket ticket1 = parkingAttendant.park(car1);
        assertEquals(4,parkingLotList.get(0).getAvailableSlots());
        ParkingLotTicket ticket2 = parkingAttendant.park(car2);
        ParkingLotTicket ticket3 = parkingAttendant.park(car3);
        ParkingLotTicket ticket4 = parkingAttendant.park(car4);
        assertEquals(4,parkingLotList.get(3).getAvailableSlots());

        parkingAttendant.unpark(car1, ticket1);
//        assertEquals(5,parkingLotList.get(0).getAvailableSlots());

        Car car5 = new Car("vhjhcsbjdvjhbfjbdfh");
        ParkingLotTicket ticket5 = parkingAttendant.park(car5);

//        assertEquals(4,parkingLotList.get(ticket5.getParkingLotId()).getAvailableSlots());
    }

    //    @Test
//    public void ensureParkingAttendantParksTheCar() throws ParkingNotAvailablException {
//        ParkingLot parkingLot = new ParkingLot(2, lotOwner, security);
//        ParkingAttendant  parkingAttendant = new ParkingAttendant(parkingLot);
//        Car car1 = new Car("shjcvb");
//        ParkingLotTicket ticket1 = parkingAttendant.parkTheCar(car1);
//        (parkingAttendant, times(1)).parkTheCar(car1);
//    }

//    @Test
//    public void parkingAttendantParksTheCar() {
//
//    }
//
//    @Test
//    public void findCarByTicket() {
//    }

}