package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uniandes.edu.co.parranderos.modelo.Hotel;
import uniandes.edu.co.parranderos.repositorio.HotelRepository;

import java.util.List;

@Controller
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping
    public String getAllHotels(Model model) {
        List<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "hoteles"; // Nombre del archivo de plantilla sin la extensión .html
    }
}
