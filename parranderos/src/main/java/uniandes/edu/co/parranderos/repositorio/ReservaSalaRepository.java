package uniandes.edu.co.parranderos.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.edu.co.parranderos.modelo.ReservaSala;
import java.util.Collection;

@Repository
public interface ReservaSalaRepository extends JpaRepository<ReservaSala, Long> {

    @Query(value = "SELECT * FROM reservasalas", nativeQuery = true)
    Collection<ReservaSala> darReservasSalas();
}

