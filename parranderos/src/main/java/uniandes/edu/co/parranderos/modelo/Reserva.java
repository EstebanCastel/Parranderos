package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="reservaciones")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="FECHALLEGADA")
    private Date fechaLlegada;
    @Column(name="FECHASALIDA")
    private Date fechaSalida;
    @Column(name="CANTIDADPERSONAS")
    private Integer cantidadPersonas;

    @ManyToOne
    @JoinColumn(name="hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name="planconsumo_id", referencedColumnName = "id")
    private PlanConsumo planConsumo;

    @ManyToOne
    @JoinColumn(name="tipohabitacion_id", referencedColumnName = "id")
    private TipoHabitacion tipoHabitacion;

    @ManyToOne
    @JoinColumn(name="titular_id", referencedColumnName = "cedula")
    private Cliente titular;

    @OneToMany(mappedBy="reserva")
    private List<Cliente> titulares;

    @OneToMany(mappedBy="reserva")
    private List<Cliente> acompañantes;

    @ManyToMany
    private List<Habitacion> habitaciones;

    public Reserva() {;}

}
