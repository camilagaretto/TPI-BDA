package tpback.demo.services.impl;


import org.springframework.stereotype.Service;
import tpback.demo.entidades.Tarifa;
import tpback.demo.repositorios.TarifaRepositorio;
import tpback.demo.services.TarifaService;

import java.util.List;

@Service
public class TarifaServiceImpl implements TarifaService {

    private final TarifaRepositorio tarifaRepositorio;

    public TarifaServiceImpl(
            TarifaRepositorio tarifaRepositorio) {
        this.tarifaRepositorio = tarifaRepositorio;
    }
    @Override
    public Tarifa add(Tarifa entity) {
        return null;
    }

    @Override
    public void update(Tarifa entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Tarifa getById(Long id) {
        return this.tarifaRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Tarifa> getAll() {
        return  this.tarifaRepositorio.findAll();
    }
}
