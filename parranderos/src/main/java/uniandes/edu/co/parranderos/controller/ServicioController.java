package uniandes.edu.co.parranderos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.parranderos.modelo.Piscina;
import uniandes.edu.co.parranderos.repositorio.PiscinaRepository;

@Controller
public class ServicioController {

    @GetMapping("/servicios")
    public String mostrarServicios() {
        return "servicios";
    }

    @Autowired
    private PiscinaRepository piscinaRepository;
    @GetMapping("/piscina")
    public String mostrarPiscinas(Model model) {
        List<Piscina> listaPiscinas = piscinaRepository.findAll();
        model.addAttribute("piscinas", listaPiscinas);
        return "piscina";
    }

}

