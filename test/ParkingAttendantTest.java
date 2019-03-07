import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ParkingAttendantTest {

    @Test
    public void parksACarIntoAemptyParkingLot() throws ParkingNotAvailablException {
        Car maruthi = new Car("DL12344");
        ParkingLot mockedParkingLot = mock(ParkingLot.class);
        ParkingAttendant parkingAttendant = new ParkingAttendant((List<ParkingLot>) mockedParkingLot);
        ParkingLotTicket ticket = parkingAttendant.park(maruthi);
        verify(mockedParkingLot).parkTheCar(maruthi, 0);
    }
}