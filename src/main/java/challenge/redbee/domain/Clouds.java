package challenge.redbee.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
public class Clouds implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("all")
    private String cloudiness;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Clouds clouds = (Clouds) o;
        return Objects.equals(cloudiness, clouds.cloudiness);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), cloudiness);
    }
}
