package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Estadia;
import java.sql.Date;

public interface EstadiaRepository extends JpaRepository<Estadia, Long> {

    @Query(value = "SELECT * FROM estadias", nativeQuery = true)
    Collection<Estadia> darEstadias();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO estadias (ID, RESERVA_ID, CUENTACONSUMO_ID, PAZYSALVO, CHECKIN, CHECKOUT) VALUES (:id, :reserva_id, :cuentaConsumo_id, :pazYsalvo, :checkin, :checkout)", nativeQuery = true)
    void insertarEstadia(@Param("id") Long id, @Param("reserva_id") Long reserva_id, @Param("cuentaConsumo_id") Long cuentaConsumo_id, @Param("pazYsalvo") Boolean pazYsalvo, @Param("checkin") Date checkin, @Param("checkout") Date checkout);

    @Modifying
    @Transactional
    @Query(value = "UPDATE estadias SET RESERVA_ID = :reserva_id, CUENTACONSUMO_ID = :cuentaConsumo_id, PAZYSALVO = :pazYsalvo, CHECKIN = :checkin, CHECKOUT = :checkout WHERE ID = :id", nativeQuery = true)
    void actualizarEstadia(@Param("id") Long id, @Param("reserva_id") Long reserva_id, @Param("cuentaConsumo_id") Long cuentaConsumo_id, @Param("pazYsalvo") Boolean pazYsalvo, @Param("checkin") Date checkin, @Param("checkout") Date checkout);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM estadias WHERE ID = :id", nativeQuery = true)
    void eliminarEstadia(@Param("id") Long id);
}
