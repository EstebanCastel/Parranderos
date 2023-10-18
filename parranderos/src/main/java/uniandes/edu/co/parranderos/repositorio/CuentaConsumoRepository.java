package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.parranderos.modelo.CuentaConsumo;

import java.util.Collection;

@Repository
public interface CuentaConsumoRepository extends JpaRepository<CuentaConsumo, Long> {

    @Query(value = "SELECT * FROM consumoservicio", nativeQuery = true)
    Collection<CuentaConsumo> darConsumos();

}

