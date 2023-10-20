package uniandes.edu.co.parranderos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import uniandes.edu.co.parranderos.modelo.TipoHabitacion;
import uniandes.edu.co.parranderos.repositorio.HabitacionRepository;
import uniandes.edu.co.parranderos.repositorio.TipoHabitacionRepository;

@Controller
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/tiposhabitaciones")
    public String tiposHabitaciones(Model model) {
        List<TipoHabitacion> tiposHabitaciones = new ArrayList<>(tipoHabitacionRepository.darTiposHabitaciones());
        Map<TipoHabitacion, String> habitacionesStringMap = new HashMap<>();

        for (TipoHabitacion th : tiposHabitaciones) {
            List<String> ids = th.getHabitaciones().stream().map(h -> h.getId().toString()).collect(Collectors.toList());
            habitacionesStringMap.put(th, String.join(", ", ids));
        }

        model.addAttribute("tiposhabitaciones", tiposHabitaciones);
        model.addAttribute("habitacionesStringMap", habitacionesStringMap);
        return "tiposhabitaciones";
    }

    @GetMapping("/creartipohabitacion")
    public String tipoHabitacionForm(Model model) {
        model.addAttribute("tipohabitacion", new TipoHabitacion());
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "tipoHabitacionNuevo";
    }

    @GetMapping("/editartipohabitacion/{id}")
    public String tipoHabitacionEditarForm(@PathVariable("id") Integer id, Model model) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(id).orElse(null);
        if (tipoHabitacion != null) {
            model.addAttribute("tipohabitacion", tipoHabitacion);
            model.addAttribute("habitaciones", habitacionRepository.findAll()); // Añadir la lista de habitaciones al modelo
            return "tipoHabitacionEditar";
        } else {
            return "redirect:/tiposhabitaciones";
        }
    }

    @PostMapping("/creartipohabitacion/save")
    public String tipoHabitacionGuardar(@ModelAttribute TipoHabitacion tipoHabitacion) {
        List<Long> habitacionIds = tipoHabitacion.getHabitaciones().stream()
                                                 .map(habitacion -> Long.valueOf(habitacion.getId()))
                                                 .collect(Collectors.toList());
        List<Habitacion> habitaciones = habitacionRepository.findAllById(habitacionIds);
        
        if (habitaciones.isEmpty()) {
            return "redirect:/error";
        }

        tipoHabitacion.setHabitaciones(habitaciones);
        tipoHabitacionRepository.save(tipoHabitacion);
        return "redirect:/tiposhabitaciones";
    }

    @PostMapping("/eliminartipohabitacion/{id}")
    public String tipoHabitacionEliminar(@PathVariable("id") Integer id, RedirectAttributes redirectAttrs) {
        try {
            tipoHabitacionRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar el tipo de habitación porque está asociado a otra tabla.");
            return "redirect:/tiposhabitaciones";
        }
        return "redirect:/tiposhabitaciones";
    }
}
