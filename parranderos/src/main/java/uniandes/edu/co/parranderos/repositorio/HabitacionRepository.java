package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.Habitacion;


import java.util.Collection;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<Habitacion> darTiposHabitaciones();
}

