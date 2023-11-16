package uniandes.edu.co.parranderos.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Document(collection = "hoteles")
@Getter
@Setter
public class Hotel {
    @Id
    private String id;
    private String nombre;
    private int capacidad;

}
