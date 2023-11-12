package tpback.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpback.demo.entidades.Tarifa;
import tpback.demo.services.TarifaService;

import java.util.List;

@RestController
@RequestMapping("/api/tarifas")
public class TarifaControllers {

    private TarifaService tarifasService;

    public TarifaControllers(TarifaService tarifasService) {
        this.tarifasService = tarifasService;
    }

    @GetMapping
    public ResponseEntity<List<Tarifa>> getAll() {
        List<Tarifa> values = this.tarifasService.getAll();
        return ResponseEntity.ok(values);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> buscarUnaTarifa(@PathVariable("id") long id){
        Tarifa values = this.tarifasService.getById(id);
        if (values != null)
            return ResponseEntity.ok(values);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
