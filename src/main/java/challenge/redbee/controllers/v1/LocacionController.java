package challenge.redbee.controllers.v1;

import challenge.redbee.domain.Locacion;
import challenge.redbee.repositories.LocacionRepository;
import challenge.redbee.services.LocacionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(LocacionController.URL)
@Api(value = "Location", description = "REST API for Location.", tags = { "Location" })
public class LocacionController {

    public static final String URL = "/locaciones";

    private LocacionService locacionService;
    private LocacionRepository locacionRepository;

    public LocacionController(LocacionService locacionService, LocacionRepository locacionRepository) {
        this.locacionService = locacionService;
        this.locacionRepository = locacionRepository;
    }

    @ApiOperation(value="Update Location", tags = { "Location" })
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Locacion getLocacion(@RequestBody Locacion locacion, @PathVariable Long id) {
        return  locacionService.updateLocacion(id, locacion);
    }

    @ApiOperation(value="Get Locations", tags = { "Location" })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Locacion> getLocaciones() { return locacionService.getAllLocaciones(); }

    @ApiOperation(value="Get Location By Id", tags = { "Location" })
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Locacion getLocacionById(@PathVariable Long id) { return locacionService.getLocacionById(id); }

    @ApiOperation(value="Update Location from API", tags = { "Location" })
    @GetMapping({"/{id}/update"})
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id) {
        Locacion locacion = locacionRepository.findById(id).get();
        locacionService.fetchAndCompare(locacion);
         }

    @ApiOperation(value="Create Location", tags = { "Location" })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Locacion createNewLocacion(@RequestBody Locacion locacion) { return locacionService.saveLocacion(locacion); }

    @ApiOperation(value="Delete Location", tags = { "Location" })
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteLocacion(@PathVariable Long id) { locacionService.deleteById(id); }

}
