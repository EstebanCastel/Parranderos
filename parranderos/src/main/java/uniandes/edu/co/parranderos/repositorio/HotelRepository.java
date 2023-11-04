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

    @Query(value = "WITH DiasEstadia AS (" +
                   "SELECT r.TITULAR_ID AS CLIENTE, " +
                   "SUM(TO_DATE(e.CHECKOUT, 'DD-MM-YYYY') - TO_DATE(e.CHECKIN, 'DD-MM-YYYY')) AS DIAS " +
                   "FROM reservaciones r " +
                   "JOIN estadias e ON r.ID = e.RESERVA_ID " +
                   "WHERE e.CHECKIN_REALIZADO = 1 AND e.CHECKOUT_REALIZADO = 1 " +
                   "GROUP BY r.TITULAR_ID" +
                   "), " +
                   "Consumo AS (" +
                   "SELECT c.CLIENTE, " +
                   "SUM(c.COSTOTOTAL) AS TOTALCONSUMO " +
                   "FROM cuentasconsumo c " +
                   "WHERE TO_DATE(c.FECHADELCONSUMO, 'DD-MM-YYYY') > (SYSDATE - INTERVAL '1' YEAR) " +
                   "GROUP BY c.CLIENTE" +
                   ") " +
                   "SELECT DISTINCT d.CLIENTE " +
                   "FROM DiasEstadia d " +
                   "LEFT JOIN Consumo co ON d.CLIENTE = co.CLIENTE " +
                   "WHERE d.DIAS >= 14 OR co.TOTALCONSUMO > 15000000", 
           nativeQuery = true)
    List<Long> obtenerBuenosClientes();

}
