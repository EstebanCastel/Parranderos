package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.PlanConsumo;

import java.util.Collection;

public interface PlanConsumoRepository extends JpaRepository<PlanConsumo, Long> {

    @Query(value = "SELECT * FROM planesconsumo", nativeQuery = true)
    Collection<PlanConsumo> darPlanesConsumo();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO planesconsumo (id, info, hotel_id) VALUES (:id, :info, :hotelId)", nativeQuery = true)
    void insertarPlanConsumo(@Param("id") Long id, @Param("info") String info, @Param("hotelId") Long hotelId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE planesconsumo SET info = :info, hotel_id = :hotelId WHERE id = :id", nativeQuery = true)
    void actualizarPlanConsumo(@Param("id") Long id, @Param("info") String info, @Param("hotelId") Long hotelId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM planesconsumo WHERE id = :id", nativeQuery = true)
    void eliminarPlanConsumo(@Param("id") Long id);
}
