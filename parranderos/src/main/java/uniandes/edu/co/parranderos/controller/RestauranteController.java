package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.modelo.Restaurante;
import uniandes.edu.co.parranderos.repositorio.RestauranteRepository;

@Controller
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/restaurantes")
    public String restaurantes(Model model) {
        model.addAttribute("restaurantes", restauranteRepository.darRestaurantes());
        return "restaurantes";
    }

    @GetMapping("/crearrestaurante")
    public String restauranteForm(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "restauranteNuevo";
    }
}
