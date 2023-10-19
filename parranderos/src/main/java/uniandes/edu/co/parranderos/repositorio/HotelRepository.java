package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import uniandes.edu.co.parranderos.modelo.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

