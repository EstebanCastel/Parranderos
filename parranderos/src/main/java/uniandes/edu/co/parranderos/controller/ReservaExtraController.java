package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniandes.edu.co.parranderos.modelo.ReservaExtra;
import uniandes.edu.co.parranderos.repositorio.ReservaExtraRepository;

@Controller
public class ReservaExtraController {

    @Autowired
    private ReservaExtraRepository reservaExtraRepository;

    // Existing methods...

    @GetMapping("/crearreservaextra")
    public String reservaExtraForm(Model model) {
        model.addAttribute("reservaextra", new ReservaExtra());
        return "reservaExtraNuevo";
    }

    @PostMapping("/crearreservaextra/save")
    public String reservaExtraGuardar(@ModelAttribute ReservaExtra reservaExtra) {
        reservaExtraRepository.insertarReservaExtra(reservaExtra.getId(), reservaExtra.getCosto(), reservaExtra.getReserva(), reservaExtra.getDuracion(), reservaExtra.getTitular());
        return "redirect:/reservasextras";
    }

    @GetMapping("/editarReservaExtra/{id}")
    public String reservaExtraEditarForm(@PathVariable("id") Long id, Model model) {
        ReservaExtra reservaExtra = reservaExtraRepository.findById(id).orElse(null);
        if (reservaExtra != null) {
            model.addAttribute("reservaextra", reservaExtra);
            return "reservaExtraEditar";
        } else {
            return "redirect:/reservasextras";
        }
    }

    @PostMapping("/editarReservaExtra/{id}/save")
    public String reservaExtraEditarGuardar(@PathVariable("id") Long id, @ModelAttribute ReservaExtra reservaExtra) {
        reservaExtraRepository.actualizarReservaExtra(id, reservaExtra.getCosto(), reservaExtra.getReserva(), reservaExtra.getDuracion(), reservaExtra.getTitular());
        return "redirect:/reservasextras";
    }

    @GetMapping("/eliminarReservaExtra/{id}")
    public String reservaExtraEliminar(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        try {
            reservaExtraRepository.eliminarReservaExtra(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar la reserva extra porque está siendo utilizada en otra tabla.");
            return "redirect:/reservasextras";
        }
        return "redirect:/reservasextras";
    }
}
