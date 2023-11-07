package uniandes.edu.co.parranderos.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estadias_seq_generator")
    @SequenceGenerator(name = "estadias_seq_generator", sequenceName = "estadias_SEQ", allocationSize = 1)
    private Long id;


    @Column(name="PAZYSALVO")
    private Boolean pazYsalvo;
    @Column(name="CHECKIN")
    private Date checkin;

    @Column(name="CHECKOUT")
    private Date checkout;

    
    private Boolean checkinRealizado;
    private Boolean checkoutRealizado;

    @OneToOne
    @JoinColumn(name="reserva_id", referencedColumnName = "id")
    private Reserva reserva;

    @OneToOne
    @JoinColumn(name="cuentaconsumo_id", referencedColumnName = "id")
    private CuentaConsumo cuentaConsumo;

    public Estadia(){;}
}
