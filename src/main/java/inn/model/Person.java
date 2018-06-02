package inn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @Getter @Setter
    private String username;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

}
