package challenge.redbee.controllers;

import challenge.redbee.domain.Locacion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(LocacionController.URL)
public class LocacionController {

    public static final String URL = "/locacion";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Locacion getLocacion(){RestTemplate restTemplate = new RestTemplate();
        Locacion locacion = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?appid=ac7c783b90a242b9dcbb219ff24b56b6&q=oregon,us", Locacion.class);
    return locacion;

    }

}
