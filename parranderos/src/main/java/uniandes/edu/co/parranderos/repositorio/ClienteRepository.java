package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import uniandes.edu.co.parranderos.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
