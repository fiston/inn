package inn.repository;

import inn.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r from Reservation r where r.startDate <= ?1 and r.endDate > ?1")
    List<Reservation> findByOverlappedDate(LocalDate date);

    List<Reservation> findByStartDateLessThanEqual(LocalDate date);
    List<Reservation> findByEndDateLessThanEqual(LocalDate date);

}
