package tpback.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpback.demo.entidades.Estacion;
import tpback.demo.services.EstacionService;

import java.util.List;

@RestController
@RequestMapping("/api/estaciones")
public class EstacionControllers {

    private EstacionService estacionesService;

    public EstacionControllers(EstacionService estacionesService) {
        this.estacionesService = estacionesService;
    }

    @GetMapping
    public ResponseEntity<List<Estacion>> getAll() {
        List<Estacion> values = this.estacionesService.getAll();
        return ResponseEntity.ok(values);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Estacion> buscarUnaEstacion(@PathVariable("id") long id){
        Estacion values = this.estacionesService.getById(id);
        if (values != null)
            return ResponseEntity.ok(values);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/estacionCercana/{latitud}/{longitud}")
    public ResponseEntity<Estacion> buscarEstacionMasCercana(
            @PathVariable double latitud, @PathVariable double longitud) {
        Estacion estacionCercana = this.estacionesService.findEstacionCercana(latitud, longitud);
        if (estacionCercana != null)
            return ResponseEntity.ok(estacionCercana);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PostMapping
    public ResponseEntity<Estacion> add(@RequestBody Estacion estacion) {
        Estacion estacionCreada =  this.estacionesService.add(estacion);
        if (estacionCreada != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(estacionCreada);
        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
