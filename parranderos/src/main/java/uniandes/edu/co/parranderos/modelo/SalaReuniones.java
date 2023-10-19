package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="salasreuniones")
@DiscriminatorValue("SalaReuniones")
@Getter
@Setter
public class SalaReuniones extends Servicio {

    private String capacidad;
    private Float costoHora;
    private Float costoAdicional;

    @OneToMany(mappedBy="salaReuniones")
    private List<ReservaSala> reservaSalas;

    public SalaReuniones(){;}
}

