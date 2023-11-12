package tpback.demo.services.impl;


import org.springframework.stereotype.Service;
import tpback.demo.entidades.Estacion;
import tpback.demo.repositorios.EstacionRepositorio;
import tpback.demo.services.EstacionService;

import java.util.List;

@Service
public class EstacionServiceImpl implements EstacionService {

    private final EstacionRepositorio estacionRepositorio;


    public EstacionServiceImpl(
            EstacionRepositorio estacionRepositorio) {
        this.estacionRepositorio = estacionRepositorio;
    }

    @Override
    public Estacion add(Estacion entity) {
        return this.estacionRepositorio.save(entity);


    }

    @Override
    public void update(Estacion entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Estacion getById(Long id) {
        return this.estacionRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Estacion> getAll() {
        return  this.estacionRepositorio.findAll();
    }


    //PREGUNTAR POR PERFORMANCE
    @Override
    public Estacion findEstacionCercana(double latitud, double longitud) {

        List<Estacion> estacion = this.estacionRepositorio.findAll();

        double menorDistancia = calcularDistancia(latitud, longitud, estacion.get(0).getLatitud(), estacion.get(0).getLongitud());
        Estacion estacionCercana = estacion.get(0);

        for(Estacion estacion : estacion){
            double calculoDistancia = calcularDistancia(latitud, longitud, estacion.getLatitud(), estacion.getLongitud());
            if(calculoDistancia < menorDistancia){
                menorDistancia = calculoDistancia;
                estacionCercana = estacion;
            }
        }
        return estacionCercana;

    }

    @Override
    public double calcularDistancia(double primerLatitud, double primerLongitud, double segundaLatitud, double segundaLongitud){
        double diferenciaLatitud = Math.abs(primerLatitud - segundaLatitud);
        double diferenciaLongitud = Math.abs(primerLongitud - segundaLongitud);


        double distancia = Math.sqrt(diferenciaLatitud * diferenciaLatitud + diferenciaLongitud * diferenciaLongitud);

        return distancia;
    }
}
