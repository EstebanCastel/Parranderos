package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.modelo.Bar;
import uniandes.edu.co.parranderos.repositorio.BarRepository;

@Controller
public class BarController {

    @Autowired
    private BarRepository barRepository;

    @GetMapping("/bares")
    public String bares(Model model) {
        model.addAttribute("bares", barRepository.darBares());
        return "bares";
    }

    @GetMapping("/crearbar")
    public String barForm(Model model) {
        model.addAttribute("bar", new Bar());
        return "barNuevo";
    }
}
