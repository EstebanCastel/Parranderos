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

    @PostMapping("/editartipohabitacion/{id}/save")
    public String tipoHabitacionActualizar(@PathVariable("id") Integer id, @ModelAttribute TipoHabitacion tipoHabitacion) {
        // Aquí buscas el tipo de habitación en la base de datos por su ID
        TipoHabitacion tipoHabitacionExistente = tipoHabitacionRepository.findById(id).orElse(null);
        if (tipoHabitacionExistente != null) {
            // Actualiza los valores del tipo de habitación existente con los valores del formulario
            tipoHabitacionExistente.setNombre(tipoHabitacion.getNombre());
            tipoHabitacionExistente.setCapacidad(tipoHabitacion.getCapacidad());
            tipoHabitacionExistente.setDotacion(tipoHabitacion.getDotacion());
            
            // Guarda el tipo de habitación actualizado en la base de datos
            tipoHabitacionRepository.save(tipoHabitacionExistente);
        } else {
            // Puedes manejar el caso en que el tipo de habitación no se encuentra en la base de datos
            // Por ejemplo, puedes registrar un error o redirigir al usuario a una página de error.
        }
        return "redirect:/tiposhabitaciones";
    }


    @PostMapping("/creartipohabitacion/save")
    public String tipoHabitacionGuardar(@ModelAttribute TipoHabitacion tipoHabitacion) {
        try {
            tipoHabitacionRepository.save(tipoHabitacion);
        } catch (Exception e) {
            // Aquí puedes manejar cualquier error que pueda ocurrir al guardar el tipo de habitación.
            // Por ejemplo, puedes registrar el error y/o redirigir al usuario a una página de error.
        }
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
