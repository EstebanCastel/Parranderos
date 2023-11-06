package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.Servicio;

import java.sql.Date;
import java.util.List;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicio, Long> {

    @Query(value = 
    "SELECT " +
    "    SERVICIO_TYPE AS NOMBRESERVICIO, " +
    "    COUNT(*) AS VECESSOLICITADO " +
    "FROM " +
    "    solicitudes_servicios " +
    "WHERE " +
    "    FECHA_SOLICITUD BETWEEN :fechaInicio AND :fechaFin " +
    "GROUP BY " +
    "    SERVICIO_TYPE " +
    "ORDER BY " +
    "    VECESSOLICITADO DESC " +
    "FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
    List<Object[]> serviciosMasSolicitados(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query(value = 
    "SELECT NOMBRESERVICIO, VECESSOLICITADO " +
    "FROM ( " +
    "    SELECT SERVICIO_TYPE AS NOMBRESERVICIO, COUNT(*) AS VECESSOLICITADO " +
    "    FROM solicitudes_servicios " +
    "    WHERE FECHA_SOLICITUD BETWEEN :fechaInicio AND :fechaFin " +
    "    GROUP BY SERVICIO_TYPE " +
    "    HAVING COUNT(*) < (3 * 52) " +
    "    ORDER BY VECESSOLICITADO ASC " +
    ") " +
    "WHERE ROWNUM <= 3", nativeQuery = true)
    List<Object[]> serviciosMenosSolicitados(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

}
