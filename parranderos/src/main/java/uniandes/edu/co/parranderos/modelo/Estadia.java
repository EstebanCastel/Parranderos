package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="estadias")
@Getter
@Setter
public class Estadia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="reserva_id", referencedColumnName = "id")
    private Reserva reserva;

    @OneToOne
    @JoinColumn(name="cuentaconsumo_id", referencedColumnName = "id")
    private CuentaConsumo cuentaConsumo;

    public Estadia(){;}
}
