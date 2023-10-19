package uniandes.edu.co.parranderos.modelo;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="piscinas")
@DiscriminatorValue("Piscina")
@Getter
@Setter
public class Piscina extends Servicio {

    private String capacidad;
    private String profundidad;
    private Date horario;

    public Piscina(){;}
}

