package uniandes.edu.co.parranderos.repositorio;


import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import uniandes.edu.co.parranderos.modelo.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {

    @Query(value = "SELECT * FROM gyms", nativeQuery = true)
    Collection<Gym> darGyms();

}
