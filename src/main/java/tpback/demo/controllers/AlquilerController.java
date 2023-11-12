package tpback.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpback.demo.entidades.Alquiler;
import tpback.demo.services.AlquilerService;

import java.util.List;

@RestController
@RequestMapping("/api/alquiler")

public class AlquilerController {
    private AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public ResponseEntity<List<Alquiler>> getAll() {
        List<Alquiler> values = this.alquilerService.getAll();
        return ResponseEntity.ok(values);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> buscarUnAlquiler(@PathVariable("id") long id){
        Alquiler values = this.alquilerService.getById(id);
        if (values != null)
            return ResponseEntity.ok(values);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/filtro/{montoMinimo}/{montoMaximo}")
    public ResponseEntity<List<Alquiler>> buscarAlquilerEntreMonto(
            @PathVariable double montoMinimo, @PathVariable double montoMaximo) {
        if(montoMinimo > montoMaximo)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        List<Alquiler> values = this.alquilerService.buscarAlquilerEntreDosMontos(montoMinimo, montoMaximo);
        if (values != null)
            return ResponseEntity.ok(values);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Alquiler> add(@RequestBody Alquiler alquiler) {
        Alquiler alquilerCreado =  this.alquilerService.add(alquiler);
        if (alquilerCreado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(alquilerCreado);
        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/nuevoAlquiler/{idEstacion}")
    public ResponseEntity<Alquiler> nuevoAlquiler(
            @PathVariable Long idEstacion) {
        Alquiler alquilerCreado =  this.alquilerService.iniciarNuevoAlquiler(idEstacion);
        if (alquilerCreado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(alquilerCreado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/finAlquiler/{idAlquiler}/{idEstacion}/{idTarifa}")
    public ResponseEntity<Alquiler> finAlquiler(
            @PathVariable long idAlquiler, @PathVariable long idEstacion,
            @PathVariable long idTarifa, @RequestParam(required = false) String moneda) {
        if (moneda == null) {
            moneda = "Moneda Argentina";
        }

        Alquiler alquilerFinalizado = this.alquilerService.finalizarAlquiler(
                idAlquiler, idEstacion, idTarifa, moneda);
        if (alquilerFinalizado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(alquilerFinalizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public void borrarAlquiler(@PathVariable("id") long id) {
        alquilerService.delete(id);

    }

}
