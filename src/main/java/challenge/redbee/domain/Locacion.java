package challenge.redbee.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Locacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Weather[] weather;

    @OneToOne
    private Main main;

    @OneToOne
    private Coord coord;

    @OneToOne
    private Wind wind;

    @OneToOne
    private Clouds clouds;

}
