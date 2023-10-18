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
@Table(name="reservaspas")
@Getter
@Setter
public class ReservaSpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float costo;
    private Date reserva;

    @ManyToOne
    @JoinColumn(name="spa_id", referencedColumnName = "id")
    private Spa spa;

    public ReservaSpa(){;}
}

