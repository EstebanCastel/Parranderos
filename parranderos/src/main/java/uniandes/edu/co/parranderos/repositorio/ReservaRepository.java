package uniandes.edu.co.parranderos.repositorio;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = "SELECT * FROM reservaciones", nativeQuery = true)
    Collection<Reserva> darReservaciones();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservaciones (id, FECHALLEGADA, FECHASALIDA, CANTIDADPERSONAS, hotel_id, planconsumo_id, TIPOHABITACION_ID, TITULAR_ID, habitacion_id) VALUES (:id, :fechaLlegada, :fechaSalida, :cantidadPersonas, :hotel_id, :planconsumo_id, :tipoHabitacionId, :titularId, :habitacionId)", nativeQuery = true)
    void insertarReserva(@Param("id") Long id, @Param("fechaLlegada") Date fechaLlegada, @Param("fechaSalida") Date fechaSalida, @Param("cantidadPersonas") Integer cantidadPersonas, @Param("hotel_id") Long hotel_id, @Param("planconsumo_id") Long planconsumo_id, @Param("tipoHabitacionId") Integer tipoHabitacionId, @Param("titularId") Long titularId, @Param("habitacionId") Integer habitacionId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservaciones SET FECHALLEGADA = :fechaLlegada, FECHASALIDA = :fechaSalida, CANTIDADPERSONAS = :cantidadPersonas, hotel_id = :hotel_id, planconsumo_id = :planconsumo_id, TIPOHABITACION_ID = :tipoHabitacionId, TITULAR_ID = :titularId, habitacion_id = :habitacionId WHERE id = :id", nativeQuery = true)
    void actualizarReserva(@Param("id") Long id, @Param("fechaLlegada") Date fechaLlegada, @Param("fechaSalida") Date fechaSalida, @Param("cantidadPersonas") Integer cantidadPersonas, @Param("hotel_id") Long hotel_id, @Param("planconsumo_id") Long planconsumo_id, @Param("tipoHabitacionId") Integer tipoHabitacionId, @Param("titularId") Long titularId, @Param("habitacionId") Integer habitacionId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservaciones WHERE id = :id", nativeQuery = true)
    void eliminarReserva(@Param("id") Long id);

    @Query(value = """
    SELECT HABITACION_ID, 
           (COUNT(*) * 100.0 / (SELECT COUNT(*) FROM reservaciones WHERE FECHALLEGADA BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE)) AS PORCENTAJE_OCUPACION
    FROM reservaciones 
    WHERE FECHALLEGADA BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE 
    GROUP BY HABITACION_ID
    """, nativeQuery = true)
    List<Object[]> porcentajeOcupacionHabitaciones();

}
