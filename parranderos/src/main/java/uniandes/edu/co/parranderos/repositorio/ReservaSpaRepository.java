package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.ReservaSpa;
import java.util.Collection;

@Repository
public interface ReservaSpaRepository extends JpaRepository<ReservaSpa, Long> {

    @Query(value = "SELECT * FROM reservaspas", nativeQuery = true)
    Collection<ReservaSpa> darReservasSpa();
}


