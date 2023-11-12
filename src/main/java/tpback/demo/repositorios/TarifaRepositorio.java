package tpback.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpback.demo.entidades.Tarifa;

@Repository
public interface TarifaRepositorio extends JpaRepository<Tarifa, Long> {
}
