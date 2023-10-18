package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.repositorio.ReservaSalaRepository;

@Controller
public class ReservaSalaController {

    @Autowired
    private ReservaSalaRepository reservaSalaRepository;

    @GetMapping("/reservasalas")
    public String reservasSalas(Model model) {
        model.addAttribute("reservasalas", reservaSalaRepository.darReservasSalas());
        return "reservasalas";
    }
}

