package uniandes.edu.co.parranderos.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniandes.edu.co.parranderos.modelo.Hotel;

public interface HotelRepository extends MongoRepository<Hotel, String> {

}
