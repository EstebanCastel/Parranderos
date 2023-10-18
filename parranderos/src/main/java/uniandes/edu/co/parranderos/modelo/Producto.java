package uniandes.edu.co.parranderos.modelo;


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
@Table(name="productos")
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private Float valor;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="habitacion_id", referencedColumnName = "id")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name="cuentaconsumo_id", referencedColumnName = "id")
    private CuentaConsumo cuentaConsumo;

    @ManyToOne
    @JoinColumn(name="restaurante_id", referencedColumnName = "id")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name="bar_id", referencedColumnName = "id")
    private Bar bar;

    @ManyToOne
    @JoinColumn(name="supermercado_id", referencedColumnName = "id")
    private Supermercado supermercado;

    @ManyToOne
    @JoinColumn(name="tienda_id", referencedColumnName = "id")
    private Tienda tienda;

    public Producto(){;}
}

