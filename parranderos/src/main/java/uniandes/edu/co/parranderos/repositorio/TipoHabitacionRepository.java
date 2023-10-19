package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.TipoHabitacion;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Integer> {

    @Query(value = "SELECT * FROM tipohabitaciones", nativeQuery = true)
    Collection<TipoHabitacion> darTiposHabitaciones();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipohabitaciones (id, NOMBRE, CAPACIDAD, DOTACION) VALUES (:id, :nombre, :capacidad, :dotacion)", nativeQuery = true)
    void insertarTipoHabitacion(@Param("id") Integer id, @Param("nombre") String nombre, @Param("capacidad") String capacidad, @Param("dotacion") String dotacion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tipohabitaciones SET NOMBRE = :nombre, CAPACIDAD = :capacidad, DOTACION = :dotacion WHERE id = :id", nativeQuery = true)
    void actualizarTipoHabitacion(@Param("id") Integer id, @Param("nombre") String nombre, @Param("capacidad") String capacidad, @Param("dotacion") String dotacion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipohabitaciones WHERE id = :id", nativeQuery = true)
    void eliminarTipoHabitacion(@Param("id") Integer id);
}
