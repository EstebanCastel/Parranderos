package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="restaurantes")
@DiscriminatorValue("Restaurante")
@Getter
@Setter
public class Restaurante extends Servicio {

    private Integer capacidad;
    private String estilo;
    private String pp;

    @OneToMany(mappedBy="restaurante")
    private List<Producto> productos;

    public Restaurante(){;}
}

