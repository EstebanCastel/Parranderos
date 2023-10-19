package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="limpiezas")
@DiscriminatorValue("Limpieza")
@Getter
@Setter
public class Limpieza extends Servicio {

    private String tipoLimpieza;
    private Integer cantidadPrendas;
    private Integer cantidadParZapatos;
    private Float valorPrenda;
    private Float valorPorZapato;
    private Float valorLimpieza;

    @OneToMany(mappedBy="limpieza")
    private List<ReservaExtra> reservaExtras;

    public Limpieza(){;}
}

