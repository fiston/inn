package inn.ServiceTest;


import inn.model.*;
import inn.repository.RoomTypeRepository;
import inn.service.ReservationService;
import inn.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;

    @Test
    public void testSaveReservation() {
        Reservation reservation = new Reservation();
        reservationService.saveReservation(reservation);

    }

    @Test
    public void testFindRoomByNumber() {
        RoomType roomType = new RoomType();
        roomType.setPrice(200.2f);
        roomType.setCapacity(2);
        reservationService.saveRoomType(roomType);

        Room room = new Room();
        room.setRoomNumber(123);
        room.setRoomType(roomType);

        reservationService.saveRoom(room);

        Assert.assertEquals(reservationService.findRoomByNumber(123).get().getRoomType().getCapacity(), new Integer(2));
    }

    @Test
    public void testFindRoomTypeById() {
        RoomType roomType = new RoomType();
        roomType.setPrice(200.2f);
        roomType.setCapacity(2);
        reservationService.saveRoomType(roomType);

        Room room = new Room();
        room.setRoomNumber(123);
        room.setRoomType(roomType);

        reservationService.saveRoom(room);

        Assert.assertEquals(reservationService.findRoomTypeById(roomType.getId()).get().getPrice(), new Float(200.2));
    }

    @Test
    public void testFindReservationById() {
        RoomType roomType = new RoomType();
        roomType.setPrice(200.2f);
        roomType.setCapacity(2);
        reservationService.saveRoomType(roomType);

        Room room = new Room();
        room.setRoomNumber(123);
        room.setRoomType(roomType);

        reservationService.saveRoom(room);

        Reservation reservation = new Reservation();
        reservation.setRoomType(roomType);
        reservationService.saveReservation(reservation);
        Assert.assertEquals(reservationService.findReservationById(reservation.getId()).get().getRoomType().getPrice(), new Float(200.2));
    }

    @Test
    public void testFindAllReservations() {
        Assert.assertEquals(reservationService.findAllReservations().size(), 4);
    }

    public void testDeleteReservationById() {
        Reservation reservation = new Reservation();
        reservationService.saveReservation(reservation);
        reservationService.deleteReservationById(reservation.getId());
    }


    private void insertEntity() {
        RoomType roomType = new RoomType();
        roomType.setPrice(200.2f);
        roomType.setCapacity(2);
        reservationService.saveRoomType(roomType);

        Room room = new Room();
        room.setRoomNumber(123);
        room.setRoomType(roomType);

        reservationService.saveRoom(room);

    }
}
