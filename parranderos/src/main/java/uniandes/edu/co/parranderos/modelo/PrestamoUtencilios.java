package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="prestamosutencilios")
@Getter
@Setter
public class PrestamoUtencilios extends Servicio {

    private Integer cantidadUtencilios;
    private Float costoPrestamo;

    @OneToMany(mappedBy="prestamoUtencilios")
    private List<ReservaExtra> reservaExtras;

    @OneToMany(mappedBy="prestamo")
    private List<Utencilios> utencilios;

    public PrestamoUtencilios(){;}
}

