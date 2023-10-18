package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.TipoHabitacion;
import java.util.Collection;

@Repository
public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Integer> {

    @Query(value = "SELECT * FROM tipohabitaciones", nativeQuery = true)
    Collection<TipoHabitacion> darTiposHabitaciones();
}

