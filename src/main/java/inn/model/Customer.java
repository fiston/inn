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
    @Pattern(regexp = "[\\u4E00-\\u9FA5a-zA-Z+]{1,15}")//Chinese
    private String realName;

    @Column
    //@Size(min = 18, max = 18)
    @Getter @Setter
    @Pattern(regexp = "[0-9+]{18}")
    private String identityNumber;

    @Column
    @Size(min = 1, max = 1)
    @Getter @Setter
    private String gender;

    @Column
    @NotBlank
    @Size(min = 4, max = 15)
    @Getter @Setter
    private String phoneNumber;

    @Column
    @Email
    @Getter @Setter
    private String emailAddress;

    @OneToMany
    @Getter
    private List<Reservation> reservations;

}
