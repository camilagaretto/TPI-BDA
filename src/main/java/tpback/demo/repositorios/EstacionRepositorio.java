package tpback.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpback.demo.entidades.Estacion;

@Repository
public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {


}
