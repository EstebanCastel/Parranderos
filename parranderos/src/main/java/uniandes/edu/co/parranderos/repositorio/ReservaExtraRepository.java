package uniandes.edu.co.parranderos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.parranderos.modelo.ReservaExtra;

import java.util.Collection;
@Repository
public interface ReservaExtraRepository extends JpaRepository<ReservaExtra, Long> {

    @Query(value = "SELECT * FROM reservasextras", nativeQuery = true)
    Collection<ReservaExtra> darReservasExtras();
}
