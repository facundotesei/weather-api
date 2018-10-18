package challenge.redbee.repositories;

import challenge.redbee.domain.Locacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacionRepository extends JpaRepository<Locacion, Long> {
}
