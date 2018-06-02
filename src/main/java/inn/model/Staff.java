package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Staff extends Person {

    @Column(nullable = false)
    @Getter @Setter
    private String staffNumber;

    @Column(nullable = false)
    @Getter @Setter
    private Boolean isAdmin;

}
