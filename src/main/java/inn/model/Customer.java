package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Customer extends Person {

    @Column
    @NotBlank
    @Getter @Setter
    private String realName;

    @Column
    @Size(min = 18, max = 18)
    @Getter @Setter
    private String identityNumber;

    @Column
    @Size(min = 1, max = 1)
    @Getter @Setter
    private String gender;

    @Column
    @NotBlank
    @Size(min = 4)
    @Getter @Setter
    private String phoneNumber;

    @Column
    @Email
    @Getter @Setter
    private String emailAddress;

}
