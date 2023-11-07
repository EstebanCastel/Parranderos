package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.SolicitudesServicio;

import java.util.Date;
import java.util.List;

@Repository
public interface SolicitudesServicioRepository extends JpaRepository<SolicitudesServicio, Long> {


    @Query(value = "SELECT * FROM solicitudes_servicios " +
               "WHERE " +
               "(COSTO BETWEEN :precio_min AND :precio_max OR (:precio_min IS NULL AND :precio_max IS NULL)) " +
               "AND " +
               "(FECHA_SOLICITUD BETWEEN :fecha_inicio AND :fecha_fin OR (:fecha_inicio IS NULL AND :fecha_fin IS NULL)) " +
               "AND " +
               "(SERVICIO_TYPE LIKE '%' || :nombre_servicio || '%' OR :nombre_servicio IS NULL)", nativeQuery = true)
    List<SolicitudesServicio> buscarServicios(
            @Param("precio_min") Double precioMin,
            @Param("precio_max") Double precioMax,
            @Param("fecha_inicio") Date fechaInicio,
            @Param("fecha_fin") Date fechaFin,
            @Param("nombre_servicio") String nombreServicio);


}

