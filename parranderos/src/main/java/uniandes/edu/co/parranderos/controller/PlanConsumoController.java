package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.parranderos.modelo.PlanConsumo;
import uniandes.edu.co.parranderos.repositorio.PlanConsumoRepository;

@Controller
public class PlanConsumoController {

    @Autowired
    private PlanConsumoRepository planConsumoRepository;

    @GetMapping("/planesconsumo")
    public String planesConsumo(Model model) {
        model.addAttribute("planesconsumo", planConsumoRepository.darPlanesConsumo());
        return "planesconsumo";
    }

    @GetMapping("/crearplan")
    public String planForm(Model model) {
        model.addAttribute("planconsumo", new PlanConsumo());
        return "planconsumoNuevo";
    }

    @PostMapping("/crearplan/save")
    public String planGuardar(@ModelAttribute PlanConsumo planconsumo) {
        planConsumoRepository.insertarPlanConsumo(planconsumo.getId(), planconsumo.getInfo(), planconsumo.getHotel().getId());
        return "redirect:/planesconsumo";
    }

    @GetMapping("/editarPlanConsumo/{id}")
    public String planEditarForm(@PathVariable("id") Long id, Model model) {
        PlanConsumo planconsumo = planConsumoRepository.findById(id).orElse(null);
        model.addAttribute("planconsumo", planconsumo);
        return "planconsumoEditar";
    }

    @PostMapping("/editarPlanConsumo/{id}/save")
    public String planEditarGuardar(@PathVariable("id") Long id, @ModelAttribute PlanConsumo planconsumo) {
        planConsumoRepository.actualizarPlanConsumo(id, planconsumo.getInfo(), planconsumo.getHotel().getId());
        return "redirect:/planesconsumo";
    }

    @GetMapping("/eliminarPlanConsumo/{id}")
    public String planEliminar(@PathVariable("id") long id) {
        planConsumoRepository.eliminarPlanConsumo(id);
        return "redirect:/planesconsumo";
    }
}
