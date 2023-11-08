package uniandes.edu.co.parranderos.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="reservasextras")
@Getter
@Setter
public class ReservaExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float costo;

    @ManyToOne
    @JoinColumn(name="limpieza_id", referencedColumnName = "id")
    private Limpieza limpieza;

    @ManyToOne
    @JoinColumn(name="prestamoUtencilios_id", referencedColumnName = "id")
    private PrestamoUtencilios prestamoUtencilios;

    @ManyToOne
    @JoinColumn(name="cuentaconsumo_id", referencedColumnName = "id")
    private CuentaConsumo cuentaConsumo;

    private Date reserva;


    public ReservaExtra(){;}
}

