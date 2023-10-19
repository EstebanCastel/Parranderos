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
import uniandes.edu.co.parranderos.modelo.TipoHabitacion;
import uniandes.edu.co.parranderos.repositorio.TipoHabitacionRepository;

@Controller
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/tiposhabitaciones")
    public String tiposHabitaciones(Model model) {
        model.addAttribute("tiposhabitaciones", tipoHabitacionRepository.darTiposHabitaciones());
        return "tiposhabitaciones";
    }

    @GetMapping("/creartipohabitacion")
    public String tipoHabitacionForm(Model model) {
        model.addAttribute("tipohabitacion", new TipoHabitacion());
        return "tipoHabitacionNuevo";
    }

    @PostMapping("/creartipohabitacion/save")
    public String tipoHabitacionGuardar(@ModelAttribute TipoHabitacion tipoHabitacion) {
        tipoHabitacionRepository.save(tipoHabitacion);
        return "redirect:/tiposhabitaciones";
    }

    @GetMapping("/editartipohabitacion/{id}")
    public String tipoHabitacionEditarForm(@PathVariable("id") Integer id, Model model) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(id).orElse(null);
        if (tipoHabitacion != null) {
            model.addAttribute("tipohabitacion", tipoHabitacion);
            return "tipoHabitacionEditar";
        } else {
            return "redirect:/tiposhabitaciones";
        }
    }

    @PostMapping("/editartipohabitacion/{id}/save")
    public String tipoHabitacionEditarGuardar(@PathVariable("id") Integer id, @ModelAttribute TipoHabitacion tipoHabitacion) {
        tipoHabitacionRepository.actualizarTipoHabitacion(id, tipoHabitacion.getNombre(), tipoHabitacion.getCapacidad(), tipoHabitacion.getDotacion());
        return "redirect:/tiposhabitaciones";
    }

    @GetMapping("/eliminartipohabitacion/{id}")
    public String tipoHabitacionEliminar(@PathVariable("id") Integer id, RedirectAttributes redirectAttrs) {
        try {
            tipoHabitacionRepository.eliminarTipoHabitacion(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar el tipo de habitación porque está siendo utilizado en otra tabla.");
            return "redirect:/tiposhabitaciones";
        }
        return "redirect:/tiposhabitaciones";
    }
}
