package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@Repository
public interface CuentaConsumo extends JpaRepository<CuentaConsumo, Long> {

    @Query(value = "SELECT * FROM consumoservicio", nativeQuery = true)
    Collection<CuentaConsumo> darConsumos();

    @Query(value = "SELECT * FROM consumoservicio WHERE id = :id", nativeQuery = true)
    CuentaConsumo darConsumo(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO consumoservicio (fecha, id_cliente, id_servicio, descripcion, costo) VALUES (:fecha, :id_cliente, :id_servicio, :descripcion, :costo)", nativeQuery = true)
    void insertarConsumo(@Param("fecha") String fecha, @Param("id_cliente") long id_cliente, @Param("id_servicio") long id_servicio, @Param("descripcion") String descripcion, @Param("costo") double costo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE consumoservicio SET fecha = :fecha, id_cliente = :id_cliente, id_servicio = :id_servicio, descripcion = :descripcion, costo = :costo WHERE id = :id", nativeQuery = true)
    void actualizarConsumo(@Param("id") long id, @Param("fecha") String fecha, @Param("id_cliente") long id_cliente, @Param("id_servicio") long id_servicio, @Param("descripcion") String descripcion, @Param("costo") double costo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM consumoservicio WHERE id = :id", nativeQuery = true)
    void eliminarConsumo(@Param("id") long id);
}

