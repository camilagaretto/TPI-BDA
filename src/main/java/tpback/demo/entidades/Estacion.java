package tpback.demo.entidades;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "estaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Estacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long estacionId;

    @Column(name = "nombre")
    String nombre;

    @Column(name = "fecha_hora_creacion")
    LocalDateTime fechaHoraCreacion;

    @Column(name = "latitud")
    double latitud;

    @Column(name = "longitud")
    double longitud;

    @OneToMany(mappedBy = "estacionRetiro")
    private List<Alquiler> alquileresRetiro;

}
