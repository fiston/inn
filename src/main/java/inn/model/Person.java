package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id @GeneratedValue
    @Getter
    private Integer id;

    @Column(unique = true)
    @Pattern(regexp = "\\w+")
    @Getter @Setter
    private String username;

    @Column
    @NotBlank
    @Getter @Setter
    private String password;

}
