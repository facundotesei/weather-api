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
public class Main implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String humidity;
    private String pressure;
    private String temp_max;
    private String temp_min;
    private String temp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Main main = (Main) o;
        return Objects.equals(humidity, main.humidity) &&
                Objects.equals(pressure, main.pressure) &&
                Objects.equals(temp_max, main.temp_max) &&
                Objects.equals(temp_min, main.temp_min) &&
                Objects.equals(temp, main.temp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), humidity, pressure, temp_max, temp_min, temp);
    }
}
