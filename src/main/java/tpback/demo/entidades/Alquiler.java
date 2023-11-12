package tpback.demo.entidades;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "alquileres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long alquilerId;

    @Column(name = "id_cliente")
    private String idCliente;

    @Column(name = "estado")
    int estado;

    @ManyToOne
    @JoinColumn(name = "estacion_retiro")
    Estacion estacionRetiro;

    @ManyToOne
    @JoinColumn(name = "estacion_devolucion")
    Estacion estacionDevolucion;

    @Column(name = "fecha_hora_retiro")
    LocalDateTime fechaHoraRetiro;

    @Column(name = "fecha_hora_devolucion")
    LocalDateTime fechaHoraDevolucion;

    @Column(name = "monto")
    double monto;

    @ManyToOne // Establece la relación con Tarifa
    @JoinColumn(name = "id_tarifa") // Nombre de la columna que se utiliza como clave foránea
    Tarifa tarifa; // Campo que representa la relación
}
