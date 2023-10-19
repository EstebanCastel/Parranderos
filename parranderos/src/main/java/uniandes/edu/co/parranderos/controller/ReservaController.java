package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.repositorio.ReservaRepository;

@Controller
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("/reservas")
    public String reservas(Model model) {
        model.addAttribute("reservaciones", reservaRepository.darReservaciones());
        return "reservaciones";
    }
}

