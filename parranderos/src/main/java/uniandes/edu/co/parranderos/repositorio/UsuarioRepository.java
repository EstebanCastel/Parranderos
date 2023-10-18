package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.Usuario;


import java.util.Collection;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

}

