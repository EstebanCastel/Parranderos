package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.DiscriminatorValue;
import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="spas")
@DiscriminatorValue("Spa")
@Getter
@Setter
public class Spa extends Servicio {

    private String nombre;
    private String descripcion;
    private Date duracion;
    private Float costo;

    @OneToMany(mappedBy="spa")
    private List<ReservaSpa> reservaSpas;

    public Spa(){;}
}
