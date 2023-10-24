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

import uniandes.edu.co.parranderos.modelo.CuentaConsumo;
import uniandes.edu.co.parranderos.repositorio.CuentaConsumoRepository;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CuentaConsumoController {

    @Autowired
    private CuentaConsumoRepository cuentaConsumoRepository;

    @GetMapping("/cuentasconsumo")
    public String cuentasConsumo(Model model) {
        var cuentas = cuentaConsumoRepository.darConsumos();
        model.addAttribute("cuentasconsumo", cuentas);
        
        Map<Long, Long> habitacionesPorCuenta = new HashMap<>();
        for(var cuenta : cuentas) {
            Long habitacionId = cuentaConsumoRepository.findHabitacionByEstadiaId(cuenta.getEstadia().getId());
            habitacionesPorCuenta.put(cuenta.getId(), habitacionId);
        }
        model.addAttribute("habitacionesPorCuenta", habitacionesPorCuenta);
        return "cuentasconsumo";
    }

    @PostMapping("/crearcuenta/save")
    public String cuentaConsumoGuardar(@ModelAttribute CuentaConsumo cuenta) {
        try {
            cuentaConsumoRepository.insertarCuentaConsumo(cuenta.getId(), cuenta.getCostoTotal(), cuenta.getEstadia().getId());
        } catch (Exception e) {
            // Aquí puedes manejar cualquier error que pueda ocurrir al guardar la cuenta de consumo.
            // Por ejemplo, puedes registrar el error y/o redirigir al usuario a una página de error.
        }
        return "redirect:/cuentasconsumo";
    }

    @PostMapping("/editarcuenta/{id}/save")
    public String cuentaConsumoActualizar(@PathVariable("id") Long id, @ModelAttribute CuentaConsumo cuenta) {
        try {
            cuentaConsumoRepository.actualizarCuentaConsumo(id, cuenta.getCostoTotal(), cuenta.getEstadia().getId());
        } catch (Exception e) {
            // Aquí puedes manejar cualquier error que pueda ocurrir al actualizar la cuenta de consumo.
        }
        return "redirect:/cuentasconsumo";
    }

    @PostMapping("/eliminarcuenta/{id}")
    public String cuentaConsumoEliminar(@PathVariable("id") Long id, RedirectAttributes redirectAttrs) {
        try {
            cuentaConsumoRepository.eliminarCuentaConsumo(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar la cuenta de consumo porque está asociada a otra tabla.");
            return "redirect:/cuentasconsumo";
        }
        return "redirect:/cuentasconsumo";
    }
    
}
