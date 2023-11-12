package tpback.demo.services;

import tpback.demo.entidades.Alquiler;

import java.util.List;

public interface AlquilerService extends Service<Alquiler, Long>{

    List<Alquiler> buscarAlquilerEntreDosMontos(double montoInicial, double montoFinal);

    Alquiler iniciarNuevoAlquiler(Long idEstacion);

    Alquiler finalizarAlquiler(Long idAlquiler, Long idEstacion, Long idTarifa, String tipoMoneda);

}

