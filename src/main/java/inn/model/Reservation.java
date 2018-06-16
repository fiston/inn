package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id @GeneratedValue
    @Getter
    private Integer id;

    @ManyToOne @JoinColumn
    @Getter @Setter
    private Customer customer;

    @ManyToOne @JoinColumn
    @Getter @Setter
    private RoomType roomType;

    @Column
    @Getter @Setter
    private LocalDate startDate;

    @Column
    @Getter @Setter
    private LocalDate endDate;

    @Column
    @Getter @Setter
    private LocalDateTime reservationTime;

    @ManyToOne @JoinColumn
    @Getter @Setter
    private Room allocatedRoom;

    @ManyToOne @JoinColumn
    @Getter @Setter
    private Staff checkinStaff;

    @Column
    @Getter @Setter
    private LocalDateTime checkinTime;

    @ManyToOne @JoinColumn
    @Getter @Setter
    private Staff checkoutStaff;

    @Column
    @Getter @Setter
    private LocalDateTime checkoutTime;

}
