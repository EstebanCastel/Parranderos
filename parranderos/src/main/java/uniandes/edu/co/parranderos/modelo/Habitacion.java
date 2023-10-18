package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="habitaciones")
@Getter
@Setter
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Float costoNoche;
    private String tipoHabitacion;
    private Integer capacidad;

    @OneToMany(mappedBy="habitacion")
    private List<Producto> productos;

    @ManyToMany(mappedBy="habitaciones")
    private List<Reserva> reservas;

    public Habitacion(){;}
}

