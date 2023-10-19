package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="salasconferencias")
@DiscriminatorValue("SalaConferencias")
@Getter
@Setter
public class SalaConferencias extends Servicio {

    private String capacidad;
    private Float costoHora;

    @OneToMany(mappedBy="salaConferencias")
    private List<ReservaSala> reservaSalas;

    public SalaConferencias(){;}
}

