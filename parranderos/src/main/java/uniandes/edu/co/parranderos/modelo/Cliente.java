package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="clientes")
@Getter
@Setter
public class Cliente {

    @Id
    @Column(name = "CEDULA")
    private Long cedula;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL")
    private RolCliente rol;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FECHANACIMIENTO")
    private Date fechaNacimiento;

    @Column(name = "NACIONALIDAD")
    private String nacionalidad;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONO")
    private String telefono;

    @ManyToOne
    @JoinColumn(name="RESERVA_ID", referencedColumnName = "id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name="ACOMPAÑANTE_RESERVA_ID", referencedColumnName = "id")
    private Reserva acompananteReserva;

    public Cliente(){;}

    public enum RolCliente {
        Titular, Acompañante
    }
}
