package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {

    @Id @GeneratedValue
    @Getter
    private Integer id;

    @ManyToOne
    @Getter @Setter
    private Customer customer;

    @ManyToOne
    @Getter @Setter
    private RoomType roomType;

    @Column
    @Getter @Setter
    private Date startDate;

    @Column
    @Getter @Setter
    private Date endDate;

    @Column
    @Getter @Setter
    private Date reservationTime;

    @ManyToOne
    @Getter @Setter
    private Room allocatedRoom;

    @ManyToOne
    @Getter @Setter
    private Staff checkinStaff;

    @Column
    @Getter @Setter
    private Date checkinTime;

    @ManyToOne
    @Getter @Setter
    private Staff checkoutStaff;

    @Column
    @Getter @Setter
    private Date checkoutTime;

}
