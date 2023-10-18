package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.Estadia;
import java.util.Collection;

@Repository
public interface EstadiaRepository extends JpaRepository<Estadia, Long> {

    @Query(value = "SELECT * FROM estadias", nativeQuery = true)
    Collection<Estadia> darEstadias();
}

