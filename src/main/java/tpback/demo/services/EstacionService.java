package tpback.demo.services;

import tpback.demo.entidades.Estacion;

public interface EstacionService extends Service<Estacion, Long>{

    Estacion findEstacionCercana(double latitud, double longitud);

    double calcularDistancia(double primerLatitud, double primerLongitud, double segundaLatitud, double segundaLongitud);

}
