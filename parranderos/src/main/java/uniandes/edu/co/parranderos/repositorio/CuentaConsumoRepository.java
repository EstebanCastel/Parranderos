package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.edu.co.parranderos.modelo.CuentaConsumo;
import java.util.Collection;
import java.util.List;

@Repository
public interface CuentaConsumoRepository extends JpaRepository<CuentaConsumo, Long> {

    @Query(value = "SELECT * FROM cuentasconsumo", nativeQuery = true)
    Collection<CuentaConsumo> darConsumos();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentasconsumo (ID, COSTOTOTAL, HABITACION, FECHADELCONSUMO) VALUES (:id, :costoTotal, :habitacionId, :fechaDelConsumo)", nativeQuery = true)
    void insertarCuentaConsumo(@Param("id") Long id, @Param("costoTotal") Float costoTotal, @Param("habitacionId") Integer habitacionId, @Param("fechaDelConsumo") java.sql.Date date);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentasconsumo SET COSTOTOTAL = :costoTotal, FECHADELCONSUMO = :fechaDelConsumo WHERE ID = :id", nativeQuery = true)
    void actualizarCuentaConsumo(@Param("id") Long id, @Param("costoTotal") Float costoTotal, @Param("fechaDelConsumo") java.sql.Date date);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cuentasconsumo WHERE ID = :id", nativeQuery = true)
    void eliminarCuentaConsumo(@Param("id") Long id);

    @Query(value = "SELECT HABITACION, SUM(COSTOTOTAL) AS DINERO_RECOLECTADO FROM cuentasconsumo WHERE FECHADELCONSUMO BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE GROUP BY HABITACION", nativeQuery = true)
    List<Object[]> dineroRecolectadoPorServicios();
}
