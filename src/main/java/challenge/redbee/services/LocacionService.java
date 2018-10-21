package challenge.redbee.services;

import challenge.redbee.domain.Locacion;

import java.util.List;

public interface LocacionService {

    Locacion getLocacionById(Long id);

    List<Locacion> getAllLocaciones();

    Locacion saveLocacion(Locacion locacion);

    Locacion updateLocacion(Long id, Locacion locacion);

    void deleteById(Long id);

    void updateLocaciones();

    void fetchAndCompare(Locacion locacion); //Era para Testing


}
