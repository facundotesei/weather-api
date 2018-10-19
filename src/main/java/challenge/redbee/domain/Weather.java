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
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // los  id weather se pueden repetir, fijarse como persistir solo un weather que lo compartan 1+ locacion
    private Long id;

    private String icon;
    private String description;
    private String main;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Weather weather = (Weather) o;
        return Objects.equals(icon, weather.icon) &&
                Objects.equals(description, weather.description) &&
                Objects.equals(main, weather.main);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), icon, description, main);
    }
}
