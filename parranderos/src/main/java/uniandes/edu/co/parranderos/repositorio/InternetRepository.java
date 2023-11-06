package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Internet;

public interface InternetRepository extends JpaRepository<Internet, Long> {

    @Query(value = "SELECT * FROM internets", nativeQuery = true)
    Collection<Internet> darInternets();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO internets (id, CAPACIDADGIGAS, COSTODIARIO) VALUES (:id, :capacidadGigas, :costoDiario)", nativeQuery = true)
    void insertarInternet(@Param("id") Long id, @Param("capacidadGigas") Integer capacidadGigas, @Param("costoDiario") Float costoDiario);

    @Modifying
    @Transactional
    @Query(value = "UPDATE internets SET CAPACIDADGIGAS = :capacidadGigas, COSTODIARIO = :costoDiario WHERE id = :id", nativeQuery = true)
    void actualizarInternet(@Param("id") Long id, @Param("capacidadGigas") Integer capacidadGigas, @Param("costoDiario") Float costoDiario);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM internets WHERE id = :id", nativeQuery = true)
    void eliminarInternet(@Param("id") Long id);
}
