package challenge.redbee.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mail;


    @OneToMany
    private Set<Board> boards = new HashSet<>();

    public User(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }
}
