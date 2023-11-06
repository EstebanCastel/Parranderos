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
        return "formularioServiciosMasSolicitados";  
    }

    @GetMapping("/serviciosMenosSolicitados")
    public String serviciosMenosSolicitados(
            @RequestParam(value = "fechaInicio", defaultValue = "2023-01-01") Date fechaInicio, 
            @RequestParam(value = "fechaFin", defaultValue = "2023-12-31") Date fechaFin, 
            Model model) {
        List<Object[]> resultados = serviciosRepository.serviciosMenosSolicitados(fechaInicio, fechaFin);
        model.addAttribute("resultados", resultados);
        return "formularioServiciosMenosSolicitados";  
    }

    @GetMapping("/mostrarFormularioServiciosMenosSolicitados")
    public String mostrarFormularioServiciosMenosSolicitados(Model model) {
        Date fechaInicio = Date.valueOf("2023-01-01");
        Date fechaFin = Date.valueOf("2023-12-31");
        List<Object[]> resultados = serviciosRepository.serviciosMenosSolicitados(fechaInicio, fechaFin);
        model.addAttribute("resultados", resultados);
        return "formularioServiciosMenosSolicitados";  
    }

    @GetMapping("/internet")
    public String mostrarInternet() {
        return "internet";
    }

    @GetMapping("/piscina")
    public String mostrarPiscina() {
        return "piscina";
    }


}
