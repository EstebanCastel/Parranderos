package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.modelo.Gym;
import uniandes.edu.co.parranderos.repositorio.GymRepository;

@Controller
public class GymController {

    @Autowired
    private GymRepository gymRepository;

    @GetMapping("/gyms")
    public String gyms(Model model) {
        model.addAttribute("gyms", gymRepository.darGyms());
        return "gyms";
    }

    @GetMapping("/creargym")
    public String gymForm(Model model) {
        model.addAttribute("gym", new Gym());
        return "gymNuevo";
    }
}
