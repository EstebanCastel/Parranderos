package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.edu.co.parranderos.modelo.CuentaConsumo;

import java.util.Collection;

@Repository
public interface CuentaConsumoRepository extends JpaRepository<CuentaConsumo, Long> {

    @Query(value = "SELECT * FROM cuentasconsumo", nativeQuery = true)
    Collection<CuentaConsumo> darConsumos();

    @Query(value = "SELECT r.HABITACION_ID FROM reservaciones r JOIN estadias e ON r.ID = e.RESERVA_ID WHERE e.ID = :estadiaId", nativeQuery = true)
    Long findHabitacionByEstadiaId(Long estadiaId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentasconsumo (ID, COSTOTOTAL, ESTADIA_ID, HABITACION) VALUES (:id, :costoTotal, :estadiaId, :habitacionId)", nativeQuery = true)
    void insertarCuentaConsumo(@Param("id") Long id, @Param("costoTotal") Float costoTotal, @Param("estadiaId") Long estadiaId, @Param("habitacionId") Integer habitacionId);

        
    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentasconsumo SET COSTOTOTAL = :costoTotal, ESTADIA_ID = :estadiaId WHERE ID = :id", nativeQuery = true)
    void actualizarCuentaConsumo(@Param("id") Long id, @Param("costoTotal") Float float1, @Param("estadiaId") Long estadiaId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cuentasconsumo WHERE ID = :id", nativeQuery = true)
    void eliminarCuentaConsumo(@Param("id") Long id);
}
