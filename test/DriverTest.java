import org.junit.Test;

import static org.junit.Assert.*;

public class DriverTest {

    @Test
    public void shouldBeAbleToParkTheCarWhenCarAndParkingLotAreProvided() throws ParkingNotAvailablException {
        Car car = new Car();
        Driver driver = new Driver(car);
        ParkingLot parkingLot = new ParkingLot(5);
        assertEquals(5, driver.parkCar(parkingLot));
    }


}
