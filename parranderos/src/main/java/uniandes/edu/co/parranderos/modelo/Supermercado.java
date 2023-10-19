package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="supermercados")
@DiscriminatorValue("Supermercado")
@Getter
@Setter
public class Supermercado extends Servicio {

    private Integer capacidad;
    private String pp;

    @OneToMany(mappedBy="supermercado")
    private List<Producto> productos;

    public Supermercado(){;}
}

