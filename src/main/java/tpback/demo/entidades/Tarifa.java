package tpback.demo.entidades;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tarifas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long tarifaId;

    @Column(name = "tipo_tarifa")
    int tipoTarifa;

    @Column(name = "definicion")
    String definicion;

    @Column(name = "dia_semana")
    int diaSemana;

    @Column(name = "dia_mes")
    int diaMes;

    @Column(name = "mes")
    int mes;

    @Column(name = "anio")
    int anio;

    @Column(name = "monto_fijo_alquiler")
    double montoFijoAlquiler;

    @Column(name = "monto_minuto_fraccion")
    double montoMinutoFraccion;

    @Column(name = "monto_km")
    double montoKm;

    @Column(name = "monto_hora")
    double montoHora;
}
