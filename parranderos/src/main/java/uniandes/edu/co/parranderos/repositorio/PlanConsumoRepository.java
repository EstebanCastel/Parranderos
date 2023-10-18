package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.PlanConsumo;

import java.util.Collection;

@Repository
public interface PlanConsumoRepository extends JpaRepository<PlanConsumo, Long> {

    @Query(value = "SELECT * FROM planesconsumo", nativeQuery = true)
    Collection<PlanConsumo> darPlanesConsumo();
}

