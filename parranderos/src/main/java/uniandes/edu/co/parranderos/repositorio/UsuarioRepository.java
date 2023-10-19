package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uniandes.edu.co.parranderos.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (id, NOMBRE, CORREO, ROL, HOTEL_ID) VALUES (:id, :nombre, :correo, :rol, :hotel_id)", nativeQuery = true)
    void insertarUsuario(@Param("id") Long id, @Param("nombre") String nombre, @Param("correo") String correo, @Param("rol") Long rol, @Param("hotel_id") Long hotel_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET NOMBRE = :nombre, CORREO = :correo, ROL = :rol, HOTEL_ID = :hotel_id WHERE id = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") Long id, @Param("nombre") String nombre, @Param("correo") String correo, @Param("rol") Long rol, @Param("hotel_id") Long hotel_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") Long id);
}
