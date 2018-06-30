package inn.service;

import inn.model.Reservation;
import inn.model.Room;
import inn.model.RoomType;
import inn.repository.ReservationRepository;
import inn.repository.RoomRepository;
import inn.repository.RoomTypeRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private RoomTypeRepository roomTypeRepository;
    private RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              RoomTypeRepository roomTypeRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.roomRepository = roomRepository;
    }

    public Optional<Room> findRoomByNumber(int roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    public Optional<RoomType> findRoomTypeById(int id) {
        return roomTypeRepository.findById(id);
    }

    public Optional<Reservation> findReservationById(int id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void deleteReservationById(int id) {
        reservationRepository.deleteById(id);
    }

    public void saveRoomType(RoomType roomType) { roomTypeRepository.save(roomType); }

    public void saveRoom(Room room) { roomRepository.save(room); }

    private Map<RoomType, Integer> roomTypesAndNumbers(int capacity) {
        val roomTypes = (capacity == 0) ? roomTypeRepository.findAll() : roomTypeRepository.findByCapacity(capacity);
        val map = new HashMap<RoomType, Integer>();
        for (val roomType : roomTypes) {
            map.put(roomType, roomType.getRooms().size());
        }
        return map;
    }

    private Map<RoomType, Integer> vacantRoomTypesAndNumbers(LocalDate date, int capacity) {
        val map = roomTypesAndNumbers(capacity);
        val reservations = reservationRepository.findByOverlappedDate(date);
        for (val reservation : reservations) {
            val roomType = reservation.getRoomType();
            if (map.containsKey(roomType)) map.put(roomType, map.get(roomType) - 1);
        }
        return map;
    }

    public Map<RoomType, Integer> vacantRoomTypesAndNumbers(LocalDate startDate, LocalDate endDate, int capacity) {
        val map = roomTypesAndNumbers(capacity);
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            val numbers = vacantRoomTypesAndNumbers(date, capacity);
            for (val entry : numbers.entrySet()) {
                map.put(entry.getKey(), Math.min(map.get(entry.getKey()), entry.getValue()));
            }
        }
        return map;
    }

    public List<Room> vacantRooms(LocalDate date, RoomType roomType) {
        val rooms = roomType.getRooms();
        val reservations = reservationRepository.findByOverlappedDate(date);
        for (val reservation : reservations) {
            if (reservation.getAllocatedRoom() != null) {
                rooms.remove(reservation.getAllocatedRoom());
            }
        }
        return rooms;
    }

    public List<Reservation> checkinReservations(LocalDate date) {
        val reservations = reservationRepository.findByStartDateLessThanEqual(date);
        reservations.removeIf(r -> r.getCheckinTime() != null);
        return reservations;
    }

    public List<Reservation> checkoutReservations(LocalDate date) {
        val reservations = reservationRepository.findByEndDateLessThanEqual(date);
        reservations.removeIf(r -> r.getCheckinTime() == null || r.getCheckoutTime() != null);
        return reservations;
    }

}
