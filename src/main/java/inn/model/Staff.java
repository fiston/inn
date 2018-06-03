package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Staff extends Person {

    @Column
    @NotBlank
    @Getter @Setter
    private String staffNumber;

    @Column
    @Getter @Setter
    private Boolean isAdmin;

}
