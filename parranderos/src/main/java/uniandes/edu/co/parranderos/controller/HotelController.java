package uniandes.edu.co.parranderos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.parranderos.modelo.Hotel;
import uniandes.edu.co.parranderos.repositorio.HotelRepository;

@Controller
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/resumenHotel")
    public String resumenHotel(Model model) {
        List<Hotel> hoteles = hotelRepository.findAll();
        List<Object[]> fechasMayor = hotelRepository.obtenerFechasMayorOcupacion();
        List<Object[]> fechasMenor = hotelRepository.obtenerFechasMenorOcupacion();
        List<Object[]> fechasIngresos = hotelRepository.obtenerFechasMayoresIngresos();
        
        model.addAttribute("hoteles", hoteles);
        model.addAttribute("fechasMayor", fechasMayor);
        model.addAttribute("fechasMenor", fechasMenor);
        model.addAttribute("fechasIngresos", fechasIngresos);
        
        return "resumenHotel";
    }
}

