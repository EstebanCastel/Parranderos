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

import uniandes.edu.co.parranderos.modelo.RolUsuario;
import uniandes.edu.co.parranderos.repositorio.RolUsuarioRepository;

@Controller
public class RolUsuarioController {

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @GetMapping("/rolesusuario")
    public String rolesUsuario(Model model) {
        model.addAttribute("rolesusuario", rolUsuarioRepository.darRolesUsuario());
        return "rolesusuario";
    }

    @GetMapping("/rolesusuario/new")
    public String rolUsuarioForm(Model model) {
        model.addAttribute("rolUsuario", new RolUsuario());
        return "rolUsuarioNuevo";
    }

    @PostMapping("/rolesusuario/new/save")
    public String rolUsuarioGuardar(@ModelAttribute RolUsuario rolUsuario) {
        rolUsuarioRepository.insertarRolUsuario(rolUsuario.getId(), rolUsuario.getNombreRol());
        return "redirect:/rolesusuario";
    }

    @GetMapping("/rolesusuario/{id}/edit")
    public String rolUsuarioEditarForm(@PathVariable("id") Long id, Model model) {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(id).orElse(null);
        if (rolUsuario != null) {
            model.addAttribute("rolUsuario", rolUsuario);
            return "rolUsuarioEditar";
        } else {
            return "redirect:/rolesusuario";
        }
    }

    @PostMapping("/rolesusuario/{id}/edit/save")
    public String rolUsuarioEditarGuardar(@PathVariable("id") Long id, @ModelAttribute RolUsuario rolUsuario) {
        rolUsuarioRepository.actualizarRolUsuario(id, rolUsuario.getNombreRol());
        return "redirect:/rolesusuario";
    }

    @GetMapping("/rolesusuario/{id}/delete")
    public String rolUsuarioEliminar(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        try {
            rolUsuarioRepository.eliminarRolUsuario(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar el rol porque está siendo utilizado en otra tabla.");
            return "redirect:/rolesusuario";
        }
        
        return "redirect:/rolesusuario";
    }

}
