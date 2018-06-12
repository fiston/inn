package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue
    @Getter
    private Integer id;

    @Column(unique = true)
    //@Pattern(regexp = "\\w+")
    @Pattern(regexp = "[0-9a-zA-Z_+]{4,10}")
    @Getter @Setter
    private String username;

    @Column
    @NotBlank
    @Getter @Setter
    @Pattern(regexp = "[0-9a-zA-Z_+]{4,20}")
    private String password;

}
