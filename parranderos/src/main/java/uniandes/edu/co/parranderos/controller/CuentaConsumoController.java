package uniandes.edu.co.parranderos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uniandes.edu.co.parranderos.repositorio.CuentaConsumoRepository;

@Controller
public class CuentaConsumoController {

    @Autowired
    private CuentaConsumoRepository cuentaConsumoRepository;

    @GetMapping("/cuentasconsumo")
    public String cuentasConsumo(Model model) {
        model.addAttribute("cuentasconsumo", cuentaConsumoRepository.darConsumos());
        return "cuentasconsumo";
    }
}

