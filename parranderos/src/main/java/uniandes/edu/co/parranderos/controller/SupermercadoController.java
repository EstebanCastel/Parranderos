package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.modelo.Supermercado;
import uniandes.edu.co.parranderos.repositorio.SupermercadoRepository;

@Controller
public class SupermercadoController {

    @Autowired
    private SupermercadoRepository supermercadoRepository;

    @GetMapping("/supermercados")
    public String supermercados(Model model) {
        model.addAttribute("supermercados", supermercadoRepository.darSupermercados());
        return "supermercados";
    }

    @GetMapping("/crearsupermercado")
    public String supermercadoForm(Model model) {
        model.addAttribute("supermercado", new Supermercado());
        return "supermercadoNuevo";
    }
}
