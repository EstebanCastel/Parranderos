package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tiendas")
@DiscriminatorValue("Tienda")
@Getter
@Setter
public class Tienda extends Servicio {

    private String tipo;
    private String nombre;
    private String pp;

    @OneToMany(mappedBy="tienda")
    private List<Producto> productos;

    public Tienda(){;}
}

