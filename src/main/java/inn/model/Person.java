package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    @NotBlank
    @Getter @Setter
    private String username;

    @Column
    @NotBlank
    @Getter @Setter
    private String password;

}
