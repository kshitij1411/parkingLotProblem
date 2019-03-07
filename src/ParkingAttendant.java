import java.util.List;

public class ParkingAttendant {
    private List<ParkingLot> parkingLotList;

    public ParkingAttendant(List<ParkingLot> parkingLotList) {

        this.parkingLotList = parkingLotList;
    }

    public ParkingLotTicket park(Car car) throws ParkingNotAvailablException {

        int currentMinAvailability = parkingLotList.get(0).getFilledSlots();

        ParkingLot parkingLot = parkingLotList.get(0);
        int lotId = parkingLot.getId();

        for(int i=0;i<parkingLotList.size();i++)
        {
            if(parkingLotList.get(i).getFilledSlots() > currentMinAvailability){
                currentMinAvailability = parkingLotList.get(i).getFilledSlots();
                parkingLot = parkingLotList.get(i);
                lotId = parkingLot.getId();
            }
        }

        return parkingLot.parkTheCar(car, lotId);
    }

    public void unpark(Car car, ParkingLotTicket ticket1) throws TicketNotFound {

        ParkingLot parkingLot = this.parkingLotList.get(ticket1.getParkingLotId());
        parkingLot.unParkCarGivenTicket(ticket1);

    }
}
