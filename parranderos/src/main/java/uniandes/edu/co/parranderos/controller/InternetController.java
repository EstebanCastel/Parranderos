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

import uniandes.edu.co.parranderos.modelo.Internet;
import uniandes.edu.co.parranderos.repositorio.InternetRepository;

@Controller
public class InternetController {

    @Autowired
    private InternetRepository internetRepository;

    @GetMapping("/internets")
    public String internets(Model model) {
        model.addAttribute("internets", internetRepository.darInternets());
        return "internets";
    }

    @GetMapping("/crearinternet")
    public String internetForm(Model model) {
        model.addAttribute("internet", new Internet());
        return "internetNuevo";
    }

    @PostMapping("/crearinternet/save")
    public String internetGuardar(@ModelAttribute Internet internet) {
        internetRepository.insertarInternet(internet.getId(), internet.getCapacidadGigas(), internet.getCostoDiario());
        return "redirect:/internets";
    }

    @GetMapping("/editarInternet/{id}")
    public String internetEditarForm(@PathVariable("id") Long id, Model model) {
        Internet internet = internetRepository.findById(id).orElse(null);
        if (internet != null) {
            model.addAttribute("internet", internet);
            return "internetEditar";
        } else {
            return "redirect:/internets";
        }
    }

    @PostMapping("/editarInternet/{id}/save")
    public String internetEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Internet internet) {
        internetRepository.actualizarInternet(id, internet.getCapacidadGigas(), internet.getCostoDiario());
        return "redirect:/internets";
    }

    @GetMapping("/eliminarInternet/{id}")
    public String internetEliminar(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        try {
            internetRepository.eliminarInternet(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar el servicio de Internet porque está siendo utilizado en otra tabla.");
            return "redirect:/internets";
        }
        return "redirect:/internets";
    }
}
