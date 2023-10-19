package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="internets")
@DiscriminatorValue("Internet")
@Getter
@Setter
public class Internet extends Servicio {

    private Integer capacidadGigas;
    private Float costoDiario;

    public Internet(){;}
}

