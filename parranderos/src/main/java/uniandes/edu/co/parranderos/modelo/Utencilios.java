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
@Table(name="utencilios")
@Getter
@Setter
public class Utencilios {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private Float costo;

    @ManyToOne
    @JoinColumn(name="prestamoUtencilios_id", referencedColumnName = "id")
    private PrestamoUtencilios prestamo;

    public Utencilios(){;}
}

