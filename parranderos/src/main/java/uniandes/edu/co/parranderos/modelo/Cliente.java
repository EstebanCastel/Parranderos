package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="clientes")
@Getter
@Setter
public class Cliente {

    @Id
    private Long cedula;

    private RolCliente rol;
    private String nombre;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String edad;
    private String email;
    private String telefono;

    @ManyToOne
    @JoinColumn(name="reserva_id", referencedColumnName = "id")
    private Reserva reserva;

    public Cliente(){;}
}

