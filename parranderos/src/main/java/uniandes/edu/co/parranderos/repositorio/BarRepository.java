package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uniandes.edu.co.parranderos.modelo.Bar;

public interface BarRepository extends JpaRepository<Bar, Long> {

    @Query(value = "SELECT * FROM bares2", nativeQuery = true)
    Collection<Bar> darBares();

}
