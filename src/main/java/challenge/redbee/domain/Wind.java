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
public class Wind implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String speed;
    private String deg;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Wind wind = (Wind) o;
        return Objects.equals(speed, wind.speed) &&
                Objects.equals(deg, wind.deg);
    }

    @Override
    public int hashCode() { return Objects.hash(super.hashCode(), speed, deg); }
}
