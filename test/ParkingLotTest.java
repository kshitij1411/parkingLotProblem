import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        assertEquals(10,  parkingLot.getParkingSlot(car));
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
        assertEquals(5,parkingLot.getParkingSlot(car));
        assertEquals(4,parkingLot.getParkingSlot(secondCar));

    }
}