package uniandes.edu.co.parranderos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.parranderos.modelo.Hotel;
import uniandes.edu.co.parranderos.repositorio.HotelRepository;

@Controller
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/resumenHotel")
    public String resumenHotel(Model model) {
        List<Hotel> hoteles = hotelRepository.findAll();
        List<Object[]> fechasMayor = hotelRepository.obtenerFechasMayorOcupacion();
        List<Object[]> fechasMenor = hotelRepository.obtenerFechasMenorOcupacion();
        List<Object[]> fechasIngresos = hotelRepository.obtenerFechasMayoresIngresos();
        
        model.addAttribute("hoteles", hoteles);
        model.addAttribute("fechasMayor", fechasMayor);
        model.addAttribute("fechasMenor", fechasMenor);
        model.addAttribute("fechasIngresos", fechasIngresos);
        
        return "resumenHotel";
    }

    @GetMapping("/buenosClientes")
    public String buenosClientes(Model model) {
        List<Long> buenosClientes = hotelRepository.obtenerBuenosClientes();
        model.addAttribute("buenosClientes", buenosClientes);
        
        return "buenosClientes";
    }

    @GetMapping("/resumenFuncionamiento")
    public String resumenFuncionamiento(Model model) {
        List<Object[]> consumoMaxMin = hotelRepository.obtenerConsumoMaxMinPorSemana();
        List<Object[]> habitacionMaxMin = hotelRepository.obtenerHabitacionMaxMinPorSemana();

        model.addAttribute("consumoMaxMin", consumoMaxMin);
        model.addAttribute("habitacionMaxMin", habitacionMaxMin);

        return "resumenFuncionamiento";
    }

    @GetMapping("/clientesExcelentes")
    public String clientesExcelentes(Model model) {
        List<Object[]> listaClientesExcelentes = hotelRepository.obtenerClientesExcelentes();
        model.addAttribute("clientesExcelentes", listaClientesExcelentes);
        return "clientesExcelentes"; // Asegúrate de tener una vista llamada 'clientesExcelentes.html' en tu carpeta de templates
    }
    
}
