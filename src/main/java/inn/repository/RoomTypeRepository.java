package inn.repository;

import inn.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

    List<RoomType> findByCapacity(Integer capacity);

}
