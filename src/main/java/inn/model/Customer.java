package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends Person {

    @Column(nullable = false)
    @Getter @Setter
    private String realName;

    @Column(nullable = false)
    @Getter @Setter
    private String identityNumber;

    @Column(nullable = false)
    @Getter @Setter
    private String gender;

    @Column
    @Getter @Setter
    private String phoneNumber;

    @Column
    @Getter @Setter
    private String emailAddress;

}
