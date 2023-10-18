package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="reservasalas")
@Getter
@Setter
public class ReservaSala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float costo;
    private Date reserva;

    @ManyToOne
    @JoinColumn(name="salaReuniones_id", referencedColumnName = "id")
    private SalaReuniones salaReuniones;

    @ManyToOne
    @JoinColumn(name="salaConferencias_id", referencedColumnName = "id")
    private SalaConferencias salaConferencias;

    @ManyToOne
    @JoinColumn(name="cuentaconsumo_id", referencedColumnName = "id")
    private CuentaConsumo cuentaConsumo;


    public ReservaSala(){;}
}

