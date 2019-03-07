import org.junit.Test;

import java.io.CharArrayReader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ParkingAttendantTest {
    @Test
    public void parksACarIntoAemptyParkingLot() throws ParkingNotAvailablException {
        Car maruthi = new Car("DL12344");
        ParkingLot mockedParkingLot = mock(ParkingLot.class);
        ParkingAttendant parkingAttendant = new ParkingAttendant(mockedParkingLot);
        ParkingLotTicket ticket = parkingAttendant.park(maruthi);
        verify(mockedParkingLot).parkTheCar(maruthi);
    }
}