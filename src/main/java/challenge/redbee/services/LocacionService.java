package challenge.redbee.services;

import challenge.redbee.domain.Locacion;

import java.util.HashSet;

public interface LocacionService {

    Locacion getLocacionById(Long id);

    Iterable<Locacion> getAllLocaciones();

    Locacion saveLocacion(Locacion locacion);

    void deleteById(Long id);

    void updateLocaciones();

    void fetchAndCompare(Locacion locacion);


}
