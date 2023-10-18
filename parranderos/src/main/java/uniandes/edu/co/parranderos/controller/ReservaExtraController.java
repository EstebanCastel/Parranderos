package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.repositorio.ReservaExtraRepository;

@Controller
public class ReservaExtraController {

    @Autowired
    private ReservaExtraRepository reservaExtraRepository;

    @GetMapping("/reservasextras")
    public String reservasExtras(Model model) {
        model.addAttribute("reservasextras", reservaExtraRepository.darReservasExtras());
        return "reservasextras";
    }
}

