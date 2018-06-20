package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Customer extends Person {

    @Column
    @NotBlank
    @Getter @Setter
    private String realName;

    @Column
    @Pattern(regexp = "\\d{17}[0-9Xx]")
    @Getter @Setter
    private String identityNumber;

    @Column
    @Size(min = 1, max = 1)
    @Getter @Setter
    private String gender;

    @Column
    @Getter @Setter
    private String phoneNumber;

    @Column
    @Email
    @Getter @Setter
    private String emailAddress;

    @Column
    @Getter @Setter
    private Boolean isVip;

    @OneToMany(mappedBy = "customer")
    @Getter
    private List<Reservation> reservations;

}
