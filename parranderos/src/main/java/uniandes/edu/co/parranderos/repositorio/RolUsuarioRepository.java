package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.parranderos.modelo.RolUsuario;

public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {

    @Query(value = "SELECT * FROM rolesusuario", nativeQuery = true)
    Collection<RolUsuario> darRolesUsuario();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rolesusuario (id, NOMBREROL) VALUES (:id, :nombreRol)", nativeQuery = true)
    void insertarRolUsuario(@Param("id") Long id, @Param("nombreRol") String nombreRol);

    @Modifying
    @Transactional
    @Query(value = "UPDATE rolesusuario SET NOMBREROL = :nombreRol WHERE id = :id", nativeQuery = true)
    void actualizarRolUsuario(@Param("id") Long id, @Param("nombreRol") String nombreRol);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rolesusuario WHERE id = :id", nativeQuery = true)
    void eliminarRolUsuario(@Param("id") Long id);
}
