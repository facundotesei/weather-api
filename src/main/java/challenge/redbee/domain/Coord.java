package challenge.redbee.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class Coord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lon;
    private String lat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Coord coord = (Coord) o;
        return Objects.equals(lon, coord.lon) &&
                Objects.equals(lat, coord.lat);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), lon, lat);
    }
}
