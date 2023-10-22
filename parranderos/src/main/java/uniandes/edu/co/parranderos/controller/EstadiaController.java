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
import uniandes.edu.co.parranderos.modelo.Estadia;
import uniandes.edu.co.parranderos.repositorio.EstadiaRepository;
import uniandes.edu.co.parranderos.repositorio.ReservaRepository;
import uniandes.edu.co.parranderos.repositorio.CuentaConsumoRepository;

@Controller
public class EstadiaController {

    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CuentaConsumoRepository cuentaConsumoRepository;

    @GetMapping("/estadias")
    public String estadias(Model model) {
        model.addAttribute("estadias", estadiaRepository.darEstadias());
        return "estadias";
    }

    @GetMapping("/crearestadia")
    public String estadiaForm(Model model) {
        model.addAttribute("estadia", new Estadia());
        model.addAttribute("reservaciones", reservaRepository.darReservaciones());
        model.addAttribute("consumos", cuentaConsumoRepository.darConsumos());
        return "estadiaNueva";
    }

    @PostMapping("/crearestadia/save")
    public String estadiaGuardar(@ModelAttribute Estadia estadia) {
        estadiaRepository.insertarEstadia(estadia.getId(), estadia.getReserva().getId(), estadia.getCuentaConsumo().getId(), estadia.getPazYsalvo(), estadia.getCheckin(), estadia.getCheckout());
        return "redirect:/estadias";
    }

    @GetMapping("/editarestadia/{id}")
    public String estadiaEditarForm(@PathVariable("id") Long id, Model model) {
        Estadia estadia = estadiaRepository.findById(id).orElse(null);
        if (estadia != null) {
            model.addAttribute("estadia", estadia);
            model.addAttribute("reservaciones", reservaRepository.darReservaciones());
            model.addAttribute("consumos", cuentaConsumoRepository.darConsumos());
            return "estadiaEditar";
        } else {
            return "redirect:/estadias";
        }
    }

    @PostMapping("/editarestadia/{id}/save")
    public String estadiaEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Estadia estadia) {
        estadiaRepository.actualizarEstadia(id, estadia.getReserva().getId(), estadia.getCuentaConsumo().getId(), estadia.getPazYsalvo(), estadia.getCheckin(), estadia.getCheckout());
        return "redirect:/estadias";
    }

    @GetMapping("/eliminarestadia/{id}")
    public String estadiaEliminar(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        try {
            estadiaRepository.eliminarEstadia(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar la estadia porque está siendo utilizado en otra tabla.");
            return "redirect:/estadias";
        }
        return "redirect:/estadias";
    }
}
