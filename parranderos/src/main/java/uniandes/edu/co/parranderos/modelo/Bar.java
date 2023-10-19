package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="bares2")
@DiscriminatorValue("Bar")
@Getter
@Setter
public class Bar extends Servicio {

    private Integer capacidad;
    private String estilo;
    private String pp;

    @OneToMany(mappedBy="bar")
    private List<Producto> productos;

    public Bar(){;}
}

