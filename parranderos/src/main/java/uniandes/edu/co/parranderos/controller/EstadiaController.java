package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.repositorio.EstadiaRepository;

@Controller
public class EstadiaController {

    @Autowired
    private EstadiaRepository estadiaRepository;

    @GetMapping("/estadias")
    public String estadias(Model model) {
        model.addAttribute("estadias", estadiaRepository.darEstadias());
        return "estadias";
    }
}

