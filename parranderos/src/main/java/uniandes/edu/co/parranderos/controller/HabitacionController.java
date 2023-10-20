package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniandes.edu.co.parranderos.modelo.Habitacion;
import uniandes.edu.co.parranderos.repositorio.HabitacionRepository;
import uniandes.edu.co.parranderos.repositorio.HotelRepository;
import uniandes.edu.co.parranderos.repositorio.TipoHabitacionRepository;

@Controller
public class HabitacionController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model) {
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "habitaciones";
    }

    @GetMapping("/crearhabitacion")
    public String habitacionForm(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("hoteles", hotelRepository.findAll());
        model.addAttribute("tiposHabitaciones", tipoHabitacionRepository.darTiposHabitaciones());
        return "habitacionNuevo";
    }





    @PostMapping("/crearhabitacion/save")
    public String habitacionGuardar(@ModelAttribute Habitacion habitacion) {
        habitacionRepository.insertarHabitacion(habitacion.getId(), habitacion.getCostoNoche(), habitacion.getHotel().getId(), habitacion.getTipoHabitacion().getId());
        return "redirect:/habitaciones";
    }

    @GetMapping("/editarHabitacion/{id}")
    public String habitacionEditarForm(@PathVariable("id") Long id, Model model) {
        Habitacion habitacion = habitacionRepository.findById(id).orElse(null);
        if (habitacion != null) {
            model.addAttribute("habitacion", habitacion);
            model.addAttribute("hoteles", hotelRepository.findAll());
            model.addAttribute("tiposHabitaciones", tipoHabitacionRepository.findAll());
            return "habitacionEditar";
        } else {
            return "redirect:/habitaciones";
        }
    }

    @PostMapping("/editarHabitacion/{id}/save")
    public String habitacionEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Habitacion habitacion) {
        habitacionRepository.actualizarHabitacion(id, habitacion.getCostoNoche(), habitacion.getHotel().getId(), habitacion.getTipoHabitacion().getId());
        return "redirect:/habitaciones";
    }

    @GetMapping("/eliminarHabitacion/{id}")
    public String habitacionEliminar(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        try {
            habitacionRepository.eliminarHabitacion(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar la habitación porque está siendo utilizada en otra tabla.");
            return "redirect:/habitaciones";
        }
        return "redirect:/habitaciones";
    }
}
