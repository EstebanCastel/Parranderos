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

import uniandes.edu.co.parranderos.modelo.Usuario;
import uniandes.edu.co.parranderos.repositorio.HotelRepository;
import uniandes.edu.co.parranderos.repositorio.RolUsuarioRepository;
import uniandes.edu.co.parranderos.repositorio.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        return "usuarios";
    }

    @GetMapping("/crearusuario")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("rolesusuario", rolUsuarioRepository.findAll());
        model.addAttribute("hoteles", hotelRepository.findAll());
        return "usuarioNuevo";
    }


    @PostMapping("/crearusuario/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.insertarUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreo(), usuario.getRol().getId(), usuario.getHotel().getId());
        return "redirect:/usuarios";
    }

    @GetMapping("/editarUsuario/{id}")
    public String usuarioEditarForm(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("rolesusuario", rolUsuarioRepository.findAll()); // añadir roles al modelo
            model.addAttribute("hoteles", hotelRepository.findAll()); // añadir hoteles al modelo
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
}


    @PostMapping("/editarUsuario/{id}/save")
    public String usuarioEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Usuario usuario) {
        usuarioRepository.actualizarUsuario(id, usuario.getNombre(), usuario.getCorreo(), usuario.getRol().getId(), usuario.getHotel().getId());
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String usuarioEliminar(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        try {
            usuarioRepository.eliminarUsuario(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar el usuario porque está siendo utilizado en otra tabla.");
            return "redirect:/usuarios";
        }
        return "redirect:/usuarios";
    }
}
