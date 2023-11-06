package uniandes.edu.co.parranderos.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicioController {
    @GetMapping("/servicios")
    public String mostrarServicios() {
        return "servicios";
    }
}
