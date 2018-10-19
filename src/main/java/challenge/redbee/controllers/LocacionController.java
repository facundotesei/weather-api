package challenge.redbee.controllers;

import challenge.redbee.domain.Locacion;
import challenge.redbee.domain.User;
import challenge.redbee.repositories.LocacionRepository;
import challenge.redbee.services.LocacionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(LocacionController.URL)
public class LocacionController {

    public static final String URL = "/locaciones";

    private LocacionService locacionService;
    private LocacionRepository locacionRepository;

    public LocacionController(LocacionService locacionService, LocacionRepository locacionRepository) {
        this.locacionService = locacionService;
        this.locacionRepository = locacionRepository;
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Locacion getLocacion(@RequestBody Locacion locacion, @PathVariable Long id){
        locacion.setId(id);
        return  locacionRepository.save(locacion);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Locacion> getLocaciones(){ return locacionService.getAllLocaciones(); }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Locacion getLocacionById(@PathVariable Long id){ return locacionService.getLocacionById(id); }

    @GetMapping({"/{id}/update"})   //Testing FetchAndCompare
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id){
        Locacion locacion = locacionRepository.findById(id).get();
        locacionService.fetchAndCompare(locacion);
         }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Locacion createNewLocacion(@RequestBody Locacion locacion){ return locacionService.saveLocacion(locacion); }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteLocacion(@PathVariable Long id){ locacionService.deleteById(id); }

}
