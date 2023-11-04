package uniandes.edu.co.parranderos.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.parranderos.modelo.CuentaConsumo;
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

    @GetMapping("/crearCuentaConsumo")
    public String cuentaConsumoForm(Model model) {
        model.addAttribute("cuentaConsumo", new CuentaConsumo());
        return "cuentaConsumoNuevo";
    }

    @PostMapping("/crearcuentaconsumo/save")
    public String cuentaConsumoGuardar(@ModelAttribute CuentaConsumo cuentaConsumo) {
        cuentaConsumoRepository.insertarCuentaConsumo(cuentaConsumo.getId(), cuentaConsumo.getCostoTotal(), cuentaConsumo.getHabitacion(), cuentaConsumo.getFechaDelConsumo(), cuentaConsumo.getCliente().getCedula());
        return "redirect:/cuentasconsumo";
    }

    @GetMapping("/editarCuentaConsumo/{id}")
    public String cuentaConsumoEditarForm(@PathVariable("id") Long id, Model model) {
        CuentaConsumo cuentaConsumo = cuentaConsumoRepository.findById(id).orElse(null);
        if (cuentaConsumo != null) {
            model.addAttribute("cuentaConsumo", cuentaConsumo);
            return "cuentaConsumoEditar";
        } else {
            return "redirect:/cuentasconsumo";
        }
    }

    @PostMapping("/editarCuentaConsumo/{id}/save")
    public String cuentaConsumoEditarGuardar(@PathVariable("id") Long id, @ModelAttribute CuentaConsumo cuentaConsumo) {
        cuentaConsumoRepository.actualizarCuentaConsumo(id, cuentaConsumo.getCostoTotal(), cuentaConsumo.getFechaDelConsumo(), cuentaConsumo.getCliente().getCedula());
        return "redirect:/cuentasconsumo";
    }

    @GetMapping("/eliminarCuentaConsumo/{id}")
    public String cuentaConsumoEliminar(@PathVariable("id") Long id) {
        cuentaConsumoRepository.eliminarCuentaConsumo(id);
        return "redirect:/cuentasconsumo";
    }

    @GetMapping("/dineroRecolectado")
    public String dineroRecolectado(Model model) {
        List<Object[]> resultados = cuentaConsumoRepository.dineroRecolectadoPorServicios();
        model.addAttribute("resultados", resultados);
        return "dineroRecolectado";
    }
    
    @GetMapping("/consumoPorUsuarioYFecha")
    public String obtenerConsumoPorUsuarioYFecha(@RequestParam("cedulaUsuario") Long cedulaUsuario, @RequestParam("fechaInicio") Date fechaInicio, @RequestParam("fechaFin") Date fechaFin, Model model) {
        List<Object[]> resultados = cuentaConsumoRepository.obtenerConsumoPorUsuarioYFecha(cedulaUsuario, fechaInicio, fechaFin);
        model.addAttribute("resultados", resultados);
        return "consumoPorUsuarioYFecha";
    }
}
