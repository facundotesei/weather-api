package challenge.redbee.services;

import challenge.redbee.domain.Locacion;

public interface LocacionService {

    Locacion getLocacionById(Long id);

    Iterable<Locacion> getAllLocaciones();

    Locacion saveLocacion(Locacion locacion);

    Locacion updateLocacion(Long id, Locacion locacion);

    void deleteById(Long id);

    void updateLocaciones();

    void fetchAndCompare(Locacion locacion); //Era para Testing


}
