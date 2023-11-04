package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "SELECT FECHALLEGADA, COUNT(HABITACION_ID) AS NumeroHabitacionesOcupadas " +
                   "FROM reservaciones " +
                   "GROUP BY FECHALLEGADA " +
                   "ORDER BY NumeroHabitacionesOcupadas DESC", 
           nativeQuery = true)
    List<Object[]> obtenerFechasMayorOcupacion();

    @Query(value = "SELECT FECHALLEGADA, COUNT(HABITACION_ID) AS NumeroHabitacionesOcupadas " +
                   "FROM reservaciones " +
                   "GROUP BY FECHALLEGADA " +
                   "ORDER BY NumeroHabitacionesOcupadas ASC", 
           nativeQuery = true)
    List<Object[]> obtenerFechasMenorOcupacion();

    @Query(value = "SELECT FECHADELCONSUMO, SUM(COSTOTOTAL) AS IngresosTotales " +
                   "FROM cuentasconsumo " +
                   "GROUP BY FECHADELCONSUMO " +
                   "ORDER BY IngresosTotales DESC", 
           nativeQuery = true)
    List<Object[]> obtenerFechasMayoresIngresos();
}
