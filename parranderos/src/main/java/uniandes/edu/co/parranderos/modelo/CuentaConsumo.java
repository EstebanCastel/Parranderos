package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cuentasconsumo")
@Getter
@Setter
public class CuentaConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float costoTotal;
    private Integer habitacion;

    @OneToMany(mappedBy="cuentaConsumo")
    private List<Producto> productos;

    @OneToMany(mappedBy="cuentaConsumo")
    private List<ReservaSala> reservaSalas;

    @OneToMany(mappedBy="cuentaConsumo")
    private List<ReservaExtra> reservaExtras;

    @OneToMany(mappedBy="cuentaConsumo")
    private List<ReservaSpa> reservaSpas;

    @OneToOne(mappedBy="cuentaConsumo")
    private Estadia estadia;

    public CuentaConsumo() {;}
}
