package challenge.redbee.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Locacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String name;

    @OneToMany(fetch =FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Weather> weather = new ArrayList<>();

    @OneToOne(cascade=CascadeType.ALL)
    private Main main;

    @OneToOne(cascade=CascadeType.ALL)
    private Coord coord;

    @OneToOne(cascade=CascadeType.ALL)
    private Wind wind;

    @OneToOne(cascade=CascadeType.ALL)
    private Clouds clouds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locacion locacion = (Locacion) o;
        return Objects.equals(id, locacion.id) &&
                Objects.equals(name, locacion.name) &&
                Objects.equals(main.getHumidity(), locacion.main.getHumidity()) &&
                Objects.equals(main.getPressure(), locacion.main.getPressure()) &&
                Objects.equals(main.getTemp(),locacion.main.getTemp()) &&
                Objects.equals(coord.getLat(), locacion.coord.getLat()) &&
                Objects.equals(coord.getLon(), locacion.coord.getLon()) &&
                Objects.equals(wind.getSpeed(), locacion.wind.getSpeed()) &&
                Objects.equals(clouds.getCloudiness(), locacion.clouds.getCloudiness());
    }

    @Override
    public int hashCode() { return Objects.hash(id, name, main, coord, wind, clouds); }

    @Override
    public String toString() {
        return "LOCATION { " +
                "ID = " + id +
                ", NAME =  '" + name + '\'' +
                ", WEATHER = " + weather +
                ", MAIN = " + main +
                ", COORD = " + coord +
                ", WIND = " + wind +
                ", CLOUDS = " + clouds +
                '}';
    }
}
