package uniandes.edu.co.parranderos.repositorio;

import java.sql.Date;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Piscina;

public interface PiscinaRepository extends JpaRepository<Piscina, Long> {

    @Query(value = "SELECT * FROM piscinas", nativeQuery = true)
    Collection<Piscina> darPiscinas();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO piscinas (id, CAPACIDAD, PROFUNDIDAD, HORARIO) VALUES (:id, :capacidad, :profundidad, :horario)", nativeQuery = true)
    void insertarPiscina(@Param("id") Long id, @Param("capacidad") String capacidad, @Param("profundidad") String profundidad, @Param("horario") Date horario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE piscinas SET CAPACIDAD = :capacidad, PROFUNDIDAD = :profundidad, HORARIO = :horario WHERE id = :id", nativeQuery = true)
    void actualizarPiscina(@Param("id") Long id, @Param("capacidad") String capacidad, @Param("profundidad") String profundidad, @Param("horario") Date horario);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM piscinas WHERE id = :id", nativeQuery = true)
    void eliminarPiscina(@Param("id") Long id);
}
