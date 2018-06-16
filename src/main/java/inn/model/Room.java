package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Room {

    @Id @GeneratedValue
    @Getter
    private Integer id;

    @Column
    @Getter @Setter
    private Integer roomNumber;

    @ManyToOne @JoinColumn
    @Getter @Setter
    private RoomType roomType;

}
