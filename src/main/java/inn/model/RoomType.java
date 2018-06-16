package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
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
    private Integer capacity;

    @Column
    @Getter @Setter
    private Float price;

    @Column
    @Getter @Setter
    private URL imageUrl;

    @OneToMany(mappedBy = "roomType")
    @Getter
    private List<Room> rooms;

}
