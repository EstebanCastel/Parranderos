package uniandes.edu.co.parranderos.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.parranderos.modelo.SolicitudesServicio;
import uniandes.edu.co.parranderos.repositorio.ServiciosRepository;
import uniandes.edu.co.parranderos.repositorio.SolicitudesServicioRepository;

@Controller
public class ServicioController {

    @Autowired
    private ServiciosRepository serviciosRepository;

    @Autowired
    private SolicitudesServicioRepository solicitudesservicioRepository;

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

    @GetMapping("/buscarServicios")
    public String buscarServicios(
            @RequestParam(value = "precio_min", required = false) Double precioMin,
            @RequestParam(value = "precio_max", required = false) Double precioMax,
            @RequestParam(value = "fecha_inicio", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaInicio,
            @RequestParam(value = "fecha_fin", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaFin,
            @RequestParam(value = "nombre_servicio", required = false) String nombreServicio,
            Model model) {

        List<SolicitudesServicio> servicios = solicitudesservicioRepository.buscarServicios(
                precioMin, precioMax, fechaInicio, fechaFin, nombreServicio);
        model.addAttribute("servicios", servicios);
        return "buscarServicios"; // Name of the view to render (e.g. HTML template name)
    }


    @GetMapping("/internet")
    public String mostrarInternet() {
        return "internet";
    }

    @GetMapping("/piscina")
    public String mostrarPiscina() {
        return "piscina";
    }

    @GetMapping("/gym")
    public String mostrarGym() {
        return "gym";
    }

    @GetMapping("/restaurante")
    public String mostrarRestaurante() {
        return "restaurante";
    }

    @GetMapping("/bar")
    public String mostrarBar() {
        return "bar";
    }

    @GetMapping("/supermercado")
    public String mostrarSupermercado() {
        return "supermercado";
    }

    @GetMapping("/tienda")
    public String mostrarTienda() {
        return "tienda";
    }


    @GetMapping("/salaconferencia")
    public String mostrarSalaconferencia() {
        return "salaconferencia";
    }

    @GetMapping("/salareunion")
    public String mostrarSalareunion() {
        return "salareunion";
    }

    @GetMapping("/limpieza")
    public String mostrarLimpieza() {
        return "limpieza";
    }

    @GetMapping("/prestamoutencilio")
    public String mostrarPrestamoutencilio() {
        return "prestamoutencilio";
    }

    @GetMapping("/spa")
    public String mostrarSpa() {
        return "spa";
    }

    


}
