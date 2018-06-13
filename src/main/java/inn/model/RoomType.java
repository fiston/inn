package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoomType {

    @Id @GeneratedValue
    @Getter
    private Integer id;

    @Column
    @Getter @Setter
    private String description;

    @Column
    @Getter @Setter
    private Integer bedCount;

    @Column
    @Getter @Setter
    private Float price;

    @OneToMany
    @Getter
    private List<Room> rooms;

}
