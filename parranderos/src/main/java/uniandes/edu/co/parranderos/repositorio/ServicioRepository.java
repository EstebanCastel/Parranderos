package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.Servicio;

import java.util.Collection;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<Servicio> darServicios();
}

