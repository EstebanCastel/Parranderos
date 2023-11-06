package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uniandes.edu.co.parranderos.modelo.Supermercado;

public interface SupermercadoRepository extends JpaRepository<Supermercado, Long> {

    @Query(value = "SELECT * FROM supermercados", nativeQuery = true)
    Collection<Supermercado> darSupermercados();

}
