package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.parranderos.modelo.Bebedor;

import java.util.Collection;

public interface BebedorRepository extends JpaRepository<Bebedor, Integer> {

    @Query(value = "SELECT * FROM Parranderos.bebedores", nativeQuery = true)
    Collection<Bebedor> darBebedores();

    @Query(value = "SELECT * FROM Parranderos.bebedores WHERE id = :id", nativeQuery = true)
    Bebedor darBebedor(@Param("id") long id);

    @Query(value = "SELECT * FROM Parranderos.bebedores b WHERE b.nombre LIKE '%' || :nombre || '%'", nativeQuery=true)
    Collection<Bebedor> darBebedoresPorNombre(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Parranderos.bebedores WHERE id = :id", nativeQuery = true)
    void eliminarBebedor(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Parranderos.bebedores SET nombre = :nombre, ciudad = :ciudad, presupuesto = :presupuesto WHERE id = :id", nativeQuery = true)
    void actualizarBebedor(@Param("id") long id, @Param("nombre") String nombre, @Param("ciudad") String ciudad,
            @Param("presupuesto") String presupuesto);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Parranderos.bebedores (id, nombre, ciudad, presupuesto) VALUES ( parranderos_sequence.nextval , :nombre, :ciudad, :presupuesto)", nativeQuery = true)
    void insertarBebedor(@Param("nombre") String nombre, @Param("ciudad") String ciudad,
            @Param("presupuesto") String presupuesto);

    @Query(value = "SELECT COUNT(*) " +
                   "FROM (SELECT G.id_bebedor " +
                   "FROM (SELECT B.id " +
                   "FROM Parranderos.bebidas B " +
                   "WHERE grado_alcohol = (SELECT MAX(grado_alcohol) AS GRADO_MAX FROM Parranderos.bebidas)) B " +
                   "INNER JOIN Parranderos.gustan G ON B.ID = G.id_bebida) IDB " +
                   "INNER JOIN Parranderos.bebedores B ON IDB.id_bebedor = B.id", nativeQuery = true)
    int darNumeroDeBebedoresQueGustanDeBebidasConMayorGradoAlcohol();

    @Query(value = "SELECT COUNT(*) " +
                   "FROM (SELECT G.id_bebedor " +
                   "FROM (SELECT B.id " +
                   "FROM Parranderos.bebidas B " +
                   "WHERE grado_alcohol = (SELECT MIN(grado_alcohol) AS GRADO_MIN FROM Parranderos.bebidas)) B " +
                   "INNER JOIN Parranderos.gustan G ON B.ID = G.id_bebida) IDB " +
                   "INNER JOIN Parranderos.bebedores B ON IDB.id_bebedor = B.id", nativeQuery = true)
    int darNumeroDeBebedoresQueGustanDeBebidasConMenorGradoAlcohol();

}
