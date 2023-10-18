package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.repositorio.TipoHabitacionRepository;

@Controller
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/tiposhabitaciones")
    public String tiposHabitaciones(Model model) {
        model.addAttribute("tiposhabitaciones", tipoHabitacionRepository.darTiposHabitaciones());
        return "tiposhabitaciones";
    }
}
