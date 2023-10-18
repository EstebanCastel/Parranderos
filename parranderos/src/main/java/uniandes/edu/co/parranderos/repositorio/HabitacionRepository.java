package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.Habitacion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    @Query(value = "SELECT * FROM tipos_habitacion", nativeQuery = true)
    Collection<Habitacion> darTiposHabitaciones();

    @Query(value = "SELECT * FROM tipos_habitacion WHERE id = :id", nativeQuery = true)
    Habitacion darTipoHabitacion(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tipos_habitacion (id, descripcion) VALUES (tipo_habitacion_sequence.nextval, :descripcion)", nativeQuery = true)
    void insertarTipoHabitacion(@Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tipos_habitacion SET descripcion = :descripcion WHERE id = :id", nativeQuery = true)
    void actualizarTipoHabitacion(@Param("id") long id, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tipos_habitacion WHERE id = :id", nativeQuery = true)
    void eliminarTipoHabitacion(@Param("id") long id);
}

