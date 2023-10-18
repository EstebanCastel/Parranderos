package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
