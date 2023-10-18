package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.repositorio.ReservaSpaRepository;

@Controller
public class ReservaSpaController {

    @Autowired
    private ReservaSpaRepository reservaSpaRepository;

    @GetMapping("/reservaspas")
    public String reservasPas(Model model) {
        model.addAttribute("reservaspas", reservaSpaRepository.darReservasSpa());
        return "reservaspas";
    }
}

