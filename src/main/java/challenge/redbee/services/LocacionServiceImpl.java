package challenge.redbee.services;

import challenge.redbee.domain.Locacion;
import challenge.redbee.exception.ResourceNotFoundException;
import challenge.redbee.repositories.LocacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Service
public class LocacionServiceImpl implements LocacionService {

    private static final Logger log = LoggerFactory.getLogger(LocacionServiceImpl.class);
    public static final String API_KEY = "ac7c783b90a242b9dcbb219ff24b56b6";
    public static final String ROOT_URL = "http://api.openweathermap.org/data/2.5/weather?appid="; //Env Variables

    private LocacionRepository locacionRepository;

    public LocacionServiceImpl(LocacionRepository locacionRepository) {
        this.locacionRepository = locacionRepository;
    }

    @Override
    public Locacion getLocacionById(Long id) {
        return locacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locacion Inexistente."));
    }

    @Override
    public Iterable<Locacion> getAllLocaciones() { return locacionRepository.findAll(); }

    @Override
    public Locacion saveLocacion(Locacion locacion) { return locacionRepository.save(locacion); }

    @Override
    public void deleteById(Long id) { locacionRepository.deleteById(id); }

    @Override
    public Locacion updateLocacion(Long id, Locacion locacion) {
        locacion.setId(id);
        return locacionRepository.save(locacion);
    }

    @Override
    public void updateLocaciones() {
        Set<Locacion> locaciones = new HashSet<>(locacionRepository.findAll());

        if(!locaciones.isEmpty()) {
            locaciones.stream()
                    .forEach(locacion -> fetchAndCompare(locacion));
        }
    }

    @Override
    public void fetchAndCompare(@NotNull Locacion locacion) {
        String url = String.format("%s%s&q=%s,us",ROOT_URL,API_KEY,locacion.getName());     //REFACTOR
        RestTemplate restTemplate = new RestTemplate();
        Locacion locacionApi = restTemplate.getForObject(url, Locacion.class);
        log.info(" DATA FETCHED : {} ",locacionApi );

        if(!locacion.equals(locacionApi) && !locacion.getWeather().equals(locacionApi.getWeather())){ //REFACTOR (la parte de compare en metodo separado)
            locacionApi.getClouds().setId(locacion.getClouds().getId());
            locacionApi.getCoord().setId(locacion.getCoord().getId());
            locacionApi.getMain().setId(locacion.getMain().getId());
            if(!locacionApi.getWeather().isEmpty())
                locacionApi.getWeather().get(0).setId(locacion.getWeather().get(0).getId()); //Solo Actualiza el primer elemento/
            else                                                                             //En la mayoria de los casos hay 1 solo weather.
                locacionApi.setWeather(locacion.getWeather());                              // Pero Estados Grandes(Como Texas) tienen 2.

            locacionApi.getWind().setId(locacion.getWind().getId());
            locacion = locacionRepository.save(locacionApi);
            log.info("~~~ SUCCESSFUL UPDATE! ~~~  : {} \n",locacion);
        }
        else {
            log.info(" THERE WERE NO CHANGES IN : {} \n",locacion.getName());
        }

    }
}
