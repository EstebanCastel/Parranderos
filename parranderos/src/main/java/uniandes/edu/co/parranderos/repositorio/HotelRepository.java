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

    @Query(value = "WITH Weeks AS (" +
                   "SELECT TRUNC(FECHADELCONSUMO, 'IW') AS inicioSemana," +
                   "TRUNC(FECHADELCONSUMO, 'IW') + 6 AS finSemana," +
                   "SERVICIO," +
                   "COUNT(*) AS consumo " +
                   "FROM cuentasconsumo " +
                   "GROUP BY TRUNC(FECHADELCONSUMO, 'IW'), SERVICIO)," +
                   "MaxMin AS (" +
                   "SELECT inicioSemana," +
                   "finSemana," +
                   "SERVICIO," +
                   "consumo," +
                   "ROW_NUMBER() OVER(PARTITION BY inicioSemana ORDER BY consumo DESC) AS rn_max," +
                   "ROW_NUMBER() OVER(PARTITION BY inicioSemana ORDER BY consumo ASC) AS rn_min " +
                   "FROM Weeks) " +
                   "SELECT inicioSemana," +
                   "finSemana," +
                   "MAX(CASE WHEN rn_max = 1 THEN consumo END) AS ConsumoMax," +
                   "MAX(CASE WHEN rn_max = 1 THEN SERVICIO END) AS TipoConsumoMax," +
                   "MIN(CASE WHEN rn_min = 1 THEN consumo END) AS ConsumoMin," +
                   "MIN(CASE WHEN rn_min = 1 THEN SERVICIO END) AS TipoConsumoMin " +
                   "FROM MaxMin " +
                   "GROUP BY inicioSemana, finSemana " +
                   "ORDER BY inicioSemana", 
           nativeQuery = true)
    List<Object[]> obtenerConsumoMaxMinPorSemana();

    @Query(value = "WITH Weeks AS (" +
                   "SELECT TRUNC(FECHALLEGADA, 'IW') AS inicioSemana," +
                   "TRUNC(FECHALLEGADA, 'IW') + 6 AS finSemana," +
                   "TIPOHABITACION_ID," +
                   "COUNT(*) AS reservas " +
                   "FROM reservaciones " +
                   "GROUP BY TRUNC(FECHALLEGADA, 'IW'), TIPOHABITACION_ID)," +
                   "MaxMin AS (" +
                   "SELECT inicioSemana," +
                   "finSemana," +
                   "TIPOHABITACION_ID," +
                   "reservas," +
                   "ROW_NUMBER() OVER(PARTITION BY inicioSemana ORDER BY reservas DESC) AS rn_max," +
                   "ROW_NUMBER() OVER(PARTITION BY inicioSemana ORDER BY reservas ASC) AS rn_min " +
                   "FROM Weeks) " +
                   "SELECT m.inicioSemana," +
                   "m.finSemana," +
                   "MAX(CASE WHEN m.rn_max = 1 THEN m.reservas END) AS HabitacionMax," +
                   "MAX(CASE WHEN m.rn_max = 1 THEN t.NOMBRE END) AS TipoMax," +
                   "MIN(CASE WHEN m.rn_min = 1 THEN m.reservas END) AS HabitacionMin," +
                   "MIN(CASE WHEN m.rn_min = 1 THEN t.NOMBRE END) AS TipoMin " +
                   "FROM MaxMin m " +
                   "JOIN tipohabitaciones t ON m.TIPOHABITACION_ID = t.ID " +
                   "GROUP BY m.inicioSemana, m.finSemana " +
                   "ORDER BY m.inicioSemana", 
           nativeQuery = true)
    List<Object[]> obtenerHabitacionMaxMinPorSemana();

    @Query(value = "WITH CheckInOut AS (" +
                   "SELECT r.TITULAR_ID " +
                   "FROM estadias e " +
                   "JOIN reservaciones r ON e.RESERVA_ID = r.ID " +
                   "WHERE e.CHECKIN_REALIZADO = 1 AND e.CHECKOUT_REALIZADO = 1), " +
                   "ConsumosCostosos AS (" +
                   "SELECT c.CLIENTE " +
                   "FROM cuentasconsumo c " +
                   "WHERE c.COSTOTOTAL > 300000 " +
                   "GROUP BY c.CLIENTE, TO_CHAR(c.FECHADELCONSUMO, 'YYYY'), TO_CHAR(c.FECHADELCONSUMO, 'Q') " +
                   "HAVING COUNT(DISTINCT TO_CHAR(c.FECHADELCONSUMO, 'Q')) >= 1), " +
                   "ServiciosLargos AS (" +
                   "SELECT sp.TITULAR AS CLIENTE " +
                   "FROM reservaspas sp " +
                   "WHERE sp.DURACION > 4 " +
                   "UNION " +
                   "SELECT sa.TITULAR AS CLIENTE " +
                   "FROM reservasalas sa " +
                   "WHERE sa.DURACION > 4) " +
                   "SELECT cl.CEDULA, cl.NOMBRE, " +
                   "CASE " +
                   "WHEN cl.CEDULA IN (SELECT TITULAR_ID FROM CheckInOut) THEN 'Check-in y check-out' " +
                   "WHEN cl.CEDULA IN (SELECT CLIENTE FROM ConsumosCostosos) THEN 'Consumo en servicios mayor a 300000' " +
                   "WHEN cl.CEDULA IN (SELECT CLIENTE FROM ServiciosLargos) THEN 'Reservas en spa o sala por más de 4 horas' " +
                   "END AS RAZON " +
                   "FROM clientes cl " +
                   "WHERE cl.CEDULA IN (SELECT TITULAR_ID FROM CheckInOut) " +
                   "OR cl.CEDULA IN (SELECT CLIENTE FROM ConsumosCostosos) " +
                   "OR cl.CEDULA IN (SELECT CLIENTE FROM ServiciosLargos)",
           nativeQuery = true)
    List<Object[]> obtenerClientesExcelentes();

}
