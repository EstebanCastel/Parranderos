package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    @Query(value = "SELECT * FROM habitaciones", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO habitaciones (id, COSTONOCHE, HOTEL_ID, TIPO_HABITACION_ID) VALUES (:id, :costoNoche, :hotel_id, :tipoHabitacion_id)", nativeQuery = true)
    void insertarHabitacion(@Param("id") Integer integer, @Param("costoNoche") Double costoNoche, @Param("hotel_id") Long hotel_id, @Param("tipoHabitacion_id") Integer integer2);

    @Modifying
    @Transactional
    @Query(value = "UPDATE habitaciones SET COSTONOCHE = :costoNoche, HOTEL_ID = :hotel_id, TIPO_HABITACION_ID = :tipoHabitacion_id WHERE id = :id", nativeQuery = true)
    void actualizarHabitacion(@Param("id") Long id, @Param("costoNoche") Double costoNoche, @Param("hotel_id") Long hotel_id, @Param("tipoHabitacion_id") Integer integer);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM habitaciones WHERE id = :id", nativeQuery = true)
    void eliminarHabitacion(@Param("id") Long id);
}
