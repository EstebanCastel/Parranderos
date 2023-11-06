package uniandes.edu.co.parranderos.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.parranderos.repositorio.ServiciosRepository;

@Controller
public class ServicioController {

    @Autowired
    private ServiciosRepository serviciosRepository;

    @GetMapping("/servicios")
    public String mostrarServicios() {
        return "servicios";
    }

    @GetMapping("/serviciosMasSolicitados")
    public String serviciosMasSolicitados(@RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin, Model model) {
        List<Object[]> resultados = serviciosRepository.serviciosMasSolicitados(fechaInicio, fechaFin);
        model.addAttribute("resultados", resultados);
        return "formularioServiciosMasSolicitados"; 
    }


    @GetMapping("/mostrarFormularioServiciosMasSolicitados")
    public String mostrarFormularioServiciosMasSolicitados() {
        return "formularioServiciosMasSolicitados";  // Este sería el nombre del archivo HTML del formulario para ingresar los datos de la consulta
    }

    @GetMapping("/serviciosMenosSolicitados")
    public String serviciosMenosSolicitados(@RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin, Model model) {
        List<Object[]> resultados = serviciosRepository.serviciosMenosSolicitados(fechaInicio, fechaFin);
        model.addAttribute("resultados", resultados);
        return "serviciosMenosSolicitados";  // Este sería el nombre del archivo HTML que muestra los resultados
    }

    @GetMapping("/mostrarFormularioServiciosMenosSolicitados")
    public String mostrarFormularioServiciosMenosSolicitados() {
        return "formularioServiciosMenosSolicitados";  // Este sería el nombre del archivo HTML del formulario para ingresar los datos de la consulta
    }

}
