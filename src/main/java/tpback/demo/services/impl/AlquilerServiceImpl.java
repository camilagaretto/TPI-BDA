package tpback.demo.services.impl;

import org.springframework.stereotype.Service;
import tpback.demo.entidades.Alquiler;
import tpback.demo.entidades.Estacion;
import tpback.demo.entidades.Tarifa;
import tpback.demo.repositorios.AlquilerRepositorio;
import tpback.demo.services.AlquilerService;
import tpback.demo.services.EstacionService;
import tpback.demo.services.TarifaService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlquilerServiceImpl implements AlquilerService {

    private final AlquilerRepositorio alquilerRepositorio;
    private final EstacionService estacionService;

    private final TarifaService tarifaService;


    public AlquilerServiceImpl(
            AlquilerRepositorio alquilerRepositorio,
            EstacionService estacionService,
            TarifaService tarifaService) {

        this.alquilerRepositorio = alquilerRepositorio;
        this.estacionService = estacionService;
        this.tarifaService = tarifaService;
    }

    @Override
    public Alquiler add(Alquiler entity) {
        return this.alquilerRepositorio.save(entity);
    }

    @Override
    public void update(Alquiler entity) {

    }

    //SACAR DESPUES
    @Override
    public void delete(Long id) {
        Alquiler enti = this.alquilerRepositorio.findById(id).orElse(null);
        this.alquilerRepositorio.delete(enti);
    }

    @Override
    public Alquiler getById(Long id) {
        return this.alquilerRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<Alquiler> getAll() {
        return  this.alquilerRepositorio.findAll();
    }


    @Override
    public List<Alquiler> buscarAlquilerEntreDosMontos(double montoMinimo, double montoMaximo) {
            return this.alquilerRepositorio.findAlquilerByMontoBetween(montoMinimo, montoMaximo);
    }


    //CAMBIAR ESTA FUNCION DESPUES POR PERFORMANCE
    @Override
    public Alquiler iniciarNuevoAlquiler(Long idEstacion) {

        Estacion estacionRetiro = this.estacionService.getById(idEstacion);
        Estacion estacionDevolucion = this.estacionService.getById(idEstacion);

        if(estacionRetiro != null){
            Alquiler alquiler = new Alquiler();
            LocalDateTime horaActual = LocalDateTime.now();
            alquiler.setIdCliente(null);
            alquiler.setEstado(1);
            alquiler.setMonto(0.0);
            alquiler.setFechaHoraRetiro(horaActual);
            alquiler.setEstacionRetiro(estacionRetiro);
            alquiler.setEstacionDevolucion(estacionDevolucion);
            alquiler.setMonto(0.0);

            return this.alquilerRepositorio.save(alquiler);
        }

        return null;

    }


    //Terminar despues
    @Override
    public Alquiler finalizarAlquiler(Long idAlquiler, Long idEstacion, Long idTarifa, String tipoMoneda) {
       Alquiler alquilerBuscado = this.getById(idAlquiler);

        Estacion estacionDevolucion = this.estacionService.getById(idEstacion);

        if(alquilerBuscado != null && estacionDevolucion != null){

            Estacion estacionRetiro = this.estacionService.getById(alquilerBuscado.getEstacionRetiro());
            //distancia euclidea multiplicada por 110 (seria la diferencia transformada en km segun el enunciado)
            double kmEntreEstacion = this.estacionService.calcularDistancia(estacionRetiro.getLatitud(),
                    estacionRetiro.getLongitud(), estacionDevolucion.getLatitud(),
                    estacionDevolucion.getLongitud()) * 110 ;

            LocalDateTime horaActual = LocalDateTime.now();

            alquilerBuscado.setEstado(2);
            alquilerBuscado.setEstacionDevolucion(estacionDevolucion.getId());
            alquilerBuscado.setFechaHoraDevolucion(horaActual);
            alquilerBuscado.setIdTarifa(idTarifa);


            double montoCalculado = calcularMonto(alquilerBuscado.getIdTarifa(),alquilerBuscado.getFechaHoraRetiro(),
                    alquilerBuscado.getFechaHoraDevolucion(), kmEntreEstacion);

            alquilerBuscado.setMonto(montoCalculado);

            this.alquilerRepositorio.save(alquilerBuscado);
            //double montoConvertido = c(alquilerBuscado.getMonto(), tipoMoneda);
            return alquilerBuscado;
        }

        return null;
    }

    public double calcularMonto(Long idTarifa, LocalDateTime horaInicio, LocalDateTime horaFin,
                             double kmEntreEstacion){

      Tarifa tarifaAlquiler  = this.tarifaService.getById(idTarifa);

      Duration duration = Duration.between(horaInicio, horaFin);
      long horas = duration.toHours();
      long minutos = (duration.toMinutes()) % 60;

      double montoHora = 0;
      double montoMinutos = 0;

      if(minutos >= 31){
          montoHora = ( 1 + horas) * tarifaAlquiler.getMontoHora();

        }else {
          montoHora = horas * tarifaAlquiler.getMontoHora();
          montoMinutos = minutos * tarifaAlquiler.getMontoMinutoFraccion();
      }

      return  montoHora + montoMinutos + tarifaAlquiler.getMontoFijoAlquiler() +
              tarifaAlquiler.getMontoKM() * kmEntreEstacion;

    }





    }
