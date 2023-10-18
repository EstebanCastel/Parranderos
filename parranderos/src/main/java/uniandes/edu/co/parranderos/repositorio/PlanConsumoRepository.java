package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.PlanConsumo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@Repository
public interface PlanConsumoRepository extends JpaRepository<PlanConsumo, Long> {

    @Query(value = "SELECT * FROM planesconsumo", nativeQuery = true)
    Collection<PlanConsumo> darPlanesConsumo();

    @Query(value = "SELECT * FROM planesconsumo WHERE id = :id", nativeQuery = true)
    PlanConsumo darPlanConsumo(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planesconsumo (nombre, descripcion) VALUES (:nombre, :descripcion)", nativeQuery = true)
    void insertarPlanConsumo(@Param("nombre") String nombre, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE planesconsumo SET nombre = :nombre, descripcion = :descripcion WHERE id = :id", nativeQuery = true)
    void actualizarPlanConsumo(@Param("id") long id, @Param("nombre") String nombre, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planesconsumo WHERE id = :id", nativeQuery = true)
    void eliminarPlanConsumo(@Param("id") long id);
}

