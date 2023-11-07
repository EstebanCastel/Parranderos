package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="solicitudes_servicios")
@Getter
@Setter
public class SolicitudesServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SERVICIO_TYPE")
    private String servicioType;

    @Column(name = "SERVICIO_ID")
    private Long servicioId;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_SOLICITUD")
    private Date fechaSolicitud;

    @Column(name = "COSTO")
    private Double costo;

    public SolicitudesServicio(){;}
}
