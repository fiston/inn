package inn.service;

import inn.model.Reservation;
import inn.model.RoomType;
import inn.repository.ReservationRepository;
import inn.repository.RoomRepository;
import inn.repository.RoomTypeRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<RoomType> findRoomTypeById(int id) {
        return roomTypeRepository.findById(id);
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    private Map<RoomType, Integer> roomTypesAndNumbers(int capacity) {
        val roomTypes = (capacity == 0) ? roomTypeRepository.findAll() : roomTypeRepository.findByCapacity(capacity);
        val map = new HashMap<RoomType, Integer>();
        for (val roomType : roomTypes) {
            map.put(roomType, roomType.getRooms().size());
        }
        return map;
    }

    private Map<RoomType, Integer> vacantRoomNumbers(LocalDate date, int capacity) {
        val map = roomTypesAndNumbers(capacity);
        val reservations = reservationRepository.findByOverlappedDate(date);
        for (val reservation : reservations) {
            val roomType = reservation.getRoomType();
            map.put(roomType, map.get(roomType) - 1);
        }
        return map;
    }

    public Map<RoomType, Integer> vacantRoomNumbers(LocalDate startDate, LocalDate endDate, int capacity) {
        val map = roomTypesAndNumbers(capacity);
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            val numbers = vacantRoomNumbers(date, capacity);
            for (val entry : numbers.entrySet()) {
                map.put(entry.getKey(), Math.min(map.get(entry.getKey()), entry.getValue()));
            }
        }
        return map;
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
