package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="gyms")
@Getter
@Setter
public class Gym extends Servicio {

    private String capacidad;
    private String maquinas;
    private Date horario;

    public Gym(){;}
}

