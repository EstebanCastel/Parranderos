package uniandes.edu.co.parranderos.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.ReservaExtra;

public interface ReservaExtraRepository extends JpaRepository<ReservaExtra, Long> {

    @Query(value = "SELECT * FROM reservasextras", nativeQuery = true)
    Collection<ReservaExtra> darReservasExtras();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservasextras (ID, COSTO, RESERVA, DURACION, TITULAR) VALUES (:id, :costo, TO_DATE(:reserva, 'DD-MON-YY'), :duracion, :titular)", nativeQuery = true)
    void insertarReservaExtra(@Param("id") Long id, @Param("costo") Float float1, @Param("reserva") Date date, @Param("duracion") Integer duracion, @Param("titular") Long titular);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservasextras SET COSTO = :costo, RESERVA = TO_DATE(:reserva, 'DD-MON-YY'), DURACION = :duracion, TITULAR = :titular WHERE ID = :id", nativeQuery = true)
    void actualizarReservaExtra(@Param("id") Long id, @Param("costo") Float float1, @Param("reserva") Date date, @Param("duracion") Integer duracion, @Param("titular") Long titular);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservasextras WHERE ID = :id", nativeQuery = true)
    void eliminarReservaExtra(@Param("id") Long id);
}
