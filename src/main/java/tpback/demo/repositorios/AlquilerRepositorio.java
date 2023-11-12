package tpback.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpback.demo.entidades.Alquiler;

import java.util.List;

@Repository
public interface AlquilerRepositorio extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findAlquilerByMontoBetween(double montoInicial, double montoFinal);

}

