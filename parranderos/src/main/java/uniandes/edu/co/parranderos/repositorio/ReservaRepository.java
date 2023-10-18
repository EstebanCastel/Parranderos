package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uniandes.edu.co.parranderos.modelo.Reserva;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query(value = "SELECT * FROM reservas", nativeQuery = true)
    Collection<Reserva> darReservas();

    @Query(value = "SELECT * FROM reservas WHERE id = :id", nativeQuery = true)
    Reserva darReserva(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO reservas (fecha_inicio, fecha_fin, id_cliente, id_habitacion) VALUES (:fecha_inicio, :fecha_fin, :id_cliente, :id_habitacion)", nativeQuery = true)
    void insertarReserva(@Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin, @Param("id_cliente") long id_cliente, @Param("id_habitacion") long id_habitacion);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservas SET fecha_inicio = :fecha_inicio, fecha_fin = :fecha_fin, id_cliente = :id_cliente, id_habitacion = :id_habitacion WHERE id = :id", nativeQuery = true)
    void actualizarReserva(@Param("id") long id, @Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin, @Param("id_cliente") long id_cliente, @Param("id_habitacion") long id_habitacion);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM reservas WHERE id = :id", nativeQuery = true)
    void eliminarReserva(@Param("id") long id);
}
