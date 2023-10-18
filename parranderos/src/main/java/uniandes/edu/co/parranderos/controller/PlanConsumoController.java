package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.repositorio.PlanConsumoRepository;

@Controller
public class PlanConsumoController {

    @Autowired
    private PlanConsumoRepository planConsumoRepository;

    @GetMapping("/planesconsumo")
    public String planesConsumo(Model model) {
        model.addAttribute("planesconsumo", planConsumoRepository.darPlanesConsumo());
        return "planesconsumo";
    }
}

