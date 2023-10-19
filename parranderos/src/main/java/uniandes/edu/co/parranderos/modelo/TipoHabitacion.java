package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tipohabitaciones")
@Getter
@Setter
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipohabitacion_seq_gen")
    @SequenceGenerator(name = "tipohabitacion_seq_gen", sequenceName = "SEQ_TIPOHABITACIONES_ID", allocationSize = 1)
    private Integer id;



    private String nombre;

    private String capacidad;

    private String dotacion;

    @OneToOne(mappedBy="tipoHabitacion", cascade = CascadeType.ALL)
    private Habitacion habitacion;

    public TipoHabitacion() {;}
}
