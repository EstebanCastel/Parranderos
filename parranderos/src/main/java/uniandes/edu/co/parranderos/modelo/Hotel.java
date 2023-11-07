package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="hoteles")
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer capacidad;

    @OneToMany(mappedBy="hotel")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy="hotel")
    private List<PlanConsumo> planesConsumo;

    @OneToMany(mappedBy="hotel")
    private List<Habitacion> habitaciones;

    @OneToMany(mappedBy="hotel")
    private List<Reserva> reservas;

    public Hotel(){;}
}
