package uniandes.edu.co.parranderos.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.parranderos.modelo.Bar;

public interface BarRepository extends JpaRepository<Bar, Integer> {

    @Query(value = "SELECT * FROM Parranderos.bares", nativeQuery = true)
    Collection<Bar> darBares();

    @Query(value = "SELECT * FROM Parranderos.bares WHERE id = :id", nativeQuery = true)
    Bar darBar(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Parranderos.bares (id, nombre, ciudad, presupuesto, cant_sedes) VALUES ( parranderos_sequence.nextval , :nombre, :ciudad, :presupuesto, :cant_sedes)", nativeQuery = true)
    void insertarBar(@Param("nombre") String nombre, @Param("ciudad") String ciudad,
                    @Param("presupuesto") String presupuesto, @Param("cant_sedes") Integer cant_sedes);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Parranderos.bares SET nombre = :nombre, ciudad = :ciudad, presupuesto = :presupuesto, cant_sedes = :cant_sedes WHERE id = :id", nativeQuery = true)
    void actualizarBar(@Param("id") long id, @Param("nombre") String nombre, @Param("ciudad") String ciudad,
                      @Param("presupuesto") String presupuesto, @Param("cant_sedes") Integer cant_sedes);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Parranderos.bares WHERE id = :id", nativeQuery = true)
    void eliminarBar(@Param("id") long id);

    @Query(value = "SELECT COUNT(*)\r\n" + //
            "FROM Parranderos.bares B\r\n" + //
            "INNER JOIN Parranderos.sirven SB ON B.ID = SB.ID_BAR\r\n" + //
            "INNER JOIN Parranderos.bebidas BR ON SB.ID_BEBIDA = BR.ID\r\n" + //
            "WHERE BR.GRADO_ALCOHOL = (SELECT MAX(GRADO_ALCOHOL) FROM Parranderos.bebidas)", nativeQuery = true)
    int darNumeroDeBaresQueSirvenBebidasConMayorGradoAlcohol();

    @Query(value = "SELECT COUNT(*)\r\n" + //
            "FROM Parranderos.bares B\r\n" + //
            "INNER JOIN Parranderos.sirven SB ON B.ID = SB.ID_BAR\r\n" + //
            "INNER JOIN Parranderos.bebidas BR ON SB.ID_BEBIDA = BR.ID\r\n" + //
            "WHERE BR.GRADO_ALCOHOL = (SELECT MIN(GRADO_ALCOHOL) FROM Parranderos.bebidas)", nativeQuery = true)
    int darNumeroDeBaresQueSirvenBebidasConMenorGradoAlcohol();

    @Query(value = "SELECT B.*\r\n" + //
            "FROM Parranderos.bares B\r\n" + //
            "INNER JOIN Parranderos.sirven SB ON B.ID = SB.ID_BAR\r\n" + //
            "INNER JOIN Parranderos.bebidas BR ON SB.ID_BEBIDA = BR.ID\r\n" + //
            "INNER JOIN Parranderos.tipos_bebida TB ON BR.tipo = TB.ID\r\n" + //
            "WHERE B.CIUDAD = :ciudad AND TB.NOMBRE = :tipo", nativeQuery = true)
    Collection<Bar> darBaresPorCiudadYTipoBebida(@Param("ciudad") String ciudad, @Param("tipo") String tipo);
}
