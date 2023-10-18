package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.RolUsuario;
import java.util.Collection;

@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {

    @Query(value = "SELECT * FROM rolesusuario", nativeQuery = true)
    Collection<RolUsuario> darRolesUsuario();
}
