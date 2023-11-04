package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.edu.co.parranderos.modelo.CuentaConsumo;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Repository
public interface CuentaConsumoRepository extends JpaRepository<CuentaConsumo, Long> {

    @Query(value = "SELECT * FROM cuentasconsumo", nativeQuery = true)
    Collection<CuentaConsumo> darConsumos();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuentasconsumo (ID, COSTOTOTAL, HABITACION, FECHADELCONSUMO, CLIENTE, SERVICIO) VALUES (:id, :costoTotal, :habitacionId, :fechaDelConsumo, :clienteId, :servicio)", nativeQuery = true)
    void insertarCuentaConsumo(@Param("id") Long id, @Param("costoTotal") Float costoTotal, @Param("habitacionId") Integer habitacionId, @Param("fechaDelConsumo") Date fechaDelConsumo, @Param("clienteId") Long clienteId, @Param("servicio") String servicio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuentasconsumo SET COSTOTOTAL = :costoTotal, FECHADELCONSUMO = :fechaDelConsumo, CLIENTE = :clienteId, SERVICIO = :servicio WHERE ID = :id", nativeQuery = true)
    void actualizarCuentaConsumo(@Param("id") Long id, @Param("costoTotal") Float costoTotal, @Param("fechaDelConsumo") Date fechaDelConsumo, @Param("clienteId") Long clienteId, @Param("servicio") String servicio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cuentasconsumo WHERE ID = :id", nativeQuery = true)
    void eliminarCuentaConsumo(@Param("id") Long id);

    @Query(value = "SELECT HABITACION, SUM(COSTOTOTAL) AS DINERO_RECOLECTADO FROM cuentasconsumo WHERE FECHADELCONSUMO BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE GROUP BY HABITACION", nativeQuery = true)
    List<Object[]> dineroRecolectadoPorServicios();

    @Query(value = "SELECT c.CLIENTE, cl.NOMBRE, c.FECHADELCONSUMO, c.COSTOTOTAL FROM cuentasconsumo c JOIN Clientes cl ON c.CLIENTE = cl.CEDULA WHERE c.CLIENTE = :cedulaUsuario AND c.FECHADELCONSUMO BETWEEN :fechaInicio AND :fechaFin ORDER BY c.FECHADELCONSUMO", nativeQuery = true)
    List<Object[]> obtenerConsumoPorUsuarioYFecha(@Param("cedulaUsuario") Long cedulaUsuario, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query(value = "SELECT CLIENTE, SERVICIO, COUNT(*) AS NUMERO_DE_VECES FROM cuentasconsumo WHERE SERVICIO = :servicioSeleccionado AND FECHADELCONSUMO BETWEEN :fechaInicio AND :fechaFin GROUP BY CLIENTE, SERVICIO ORDER BY NUMERO_DE_VECES DESC", nativeQuery = true)
    List<Object[]> obtenerClientesPorServicioYFecha(@Param("servicioSeleccionado") String servicioSeleccionado, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query(value = 
    "SELECT c.CLIENTE, c.FECHADELCONSUMO, c.SERVICIO " +
    "FROM cuentasconsumo c " +
    "WHERE NOT EXISTS (" +
    "   SELECT 1 " +
    "   FROM cuentasconsumo cc " +
    "   WHERE cc.CLIENTE = c.CLIENTE " +
    "   AND cc.SERVICIO = :servicioSeleccionado " +
    "   AND cc.FECHADELCONSUMO BETWEEN :fechaInicio AND :fechaFin " +
    ") " +
    "GROUP BY c.CLIENTE, c.FECHADELCONSUMO, c.SERVICIO " +
    "ORDER BY c.CLIENTE, c.FECHADELCONSUMO DESC", nativeQuery = true)
    List<Object[]> obtenerClientesQueNoConsumieronServicio(@Param("servicioSeleccionado") String servicioSeleccionado, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

}
