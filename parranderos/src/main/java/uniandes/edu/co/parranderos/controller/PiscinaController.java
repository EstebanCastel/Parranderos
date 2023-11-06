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
import uniandes.edu.co.parranderos.modelo.Piscina;
import uniandes.edu.co.parranderos.repositorio.PiscinaRepository;

@Controller
public class PiscinaController {

    @Autowired
    private PiscinaRepository piscinaRepository;

    @GetMapping("/piscinas")
    public String piscinas(Model model) {
        model.addAttribute("piscinas", piscinaRepository.darPiscinas());
        return "piscinas";
    }

    @GetMapping("/crearpiscina")
    public String piscinaForm(Model model) {
        model.addAttribute("piscina", new Piscina());
        return "piscinaNueva";
    }

    @PostMapping("/crearpiscina/save")
    public String piscinaGuardar(@ModelAttribute Piscina piscina) {
        piscinaRepository.insertarPiscina(piscina.getId(), piscina.getCapacidad(), piscina.getProfundidad(), piscina.getHorario());
        return "redirect:/piscinas";
    }

    @GetMapping("/editarPiscina/{id}")
    public String piscinaEditarForm(@PathVariable("id") Long id, Model model) {
        Piscina piscina = piscinaRepository.findById(id).orElse(null);
        if (piscina != null) {
            model.addAttribute("piscina", piscina);
            return "piscinaEditar";
        } else {
            return "redirect:/piscinas";
        }
    }

    @PostMapping("/editarPiscina/{id}/save")
    public String piscinaEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Piscina piscina) {
        piscinaRepository.actualizarPiscina(id, piscina.getCapacidad(), piscina.getProfundidad(), piscina.getHorario());
        return "redirect:/piscinas";
    }

    @GetMapping("/eliminarPiscina/{id}")
    public String piscinaEliminar(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        try {
            piscinaRepository.eliminarPiscina(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar la piscina porque está siendo utilizada en otra tabla.");
            return "redirect:/piscinas";
        }
        return "redirect:/piscinas";
    }
}
