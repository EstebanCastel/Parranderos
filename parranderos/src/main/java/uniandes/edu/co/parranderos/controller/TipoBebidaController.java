package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.parranderos.modelo.Tipo_bebida;
import uniandes.edu.co.parranderos.repositorio.Tipo_bebidaRepository;

@Controller
public class TipoBebidaController {

    @Autowired
    private Tipo_bebidaRepository tipo_bebidaRepository;

    @GetMapping("/tipos_bebida")
    public String tiposBebida(Model model) {
        model.addAttribute("tipos_bebida", tipo_bebidaRepository.darTipos_bebida());
        return "tipos_bebida";
    }

    @GetMapping("/tipos_bebida/new")
    public String tipoBebidaForm(Model model) {
        model.addAttribute("tipo_bebida", new Tipo_bebida());
        return "tipoBebidaNuevo";
    }

    @PostMapping("/tipos_bebida/new/save")
    public String tipoBebidaGuardar(@ModelAttribute Tipo_bebida tipo_bebida) {
        tipo_bebidaRepository.save(tipo_bebida);
        return "redirect:/tipos_bebida";
    }

    @GetMapping("/tipos_bebida/{id}/edit")
    public String tipoBebidaEditarForm(@PathVariable("id") Integer id, Model model) {
        Tipo_bebida tipo_bebida = tipo_bebidaRepository.findById(id).orElse(null);
        if (tipo_bebida != null) {
            model.addAttribute("tipo_bebida", tipo_bebida);
            return "tipoBebidaEditar";
        } else {
            return "redirect:/tipos_bebida";
        }
    }

    @PostMapping("/tipos_bebida/{id}/edit/save")
    public String tipoBebidaEditarGuardar(@PathVariable("id") Integer id, @ModelAttribute Tipo_bebida tipo_bebida) {
        tipo_bebidaRepository.save(tipo_bebida);
        return "redirect:/tipos_bebida";
    }

    @GetMapping("/tipos_bebida/{id}/delete")
    public String tipoBebidaBorrar(@PathVariable("id") Integer id) {
        tipo_bebidaRepository.deleteById(id);
        return "redirect:/tipos_bebida";
    }
}
