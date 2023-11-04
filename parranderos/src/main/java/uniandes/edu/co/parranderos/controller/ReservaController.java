package uniandes.edu.co.parranderos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniandes.edu.co.parranderos.modelo.Reserva;
import uniandes.edu.co.parranderos.repositorio.EstadiaRepository;
import uniandes.edu.co.parranderos.repositorio.HabitacionRepository;
import uniandes.edu.co.parranderos.repositorio.HotelRepository;
import uniandes.edu.co.parranderos.repositorio.PlanConsumoRepository;
import uniandes.edu.co.parranderos.repositorio.ReservaRepository;
import uniandes.edu.co.parranderos.repositorio.TipoHabitacionRepository;
import uniandes.edu.co.parranderos.repositorio.ClienteRepository;

@Controller
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private PlanConsumoRepository planConsumoRepository;

    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/reservas")
    public String reservas(Model model) {
        model.addAttribute("reservaciones", reservaRepository.darReservaciones());
        return "reservaciones";
    }

    @GetMapping("/crearreserva")
    public String reservaForm(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("hoteles", hotelRepository.findAll());
        model.addAttribute("planesConsumo", planConsumoRepository.findAll());
        model.addAttribute("estadias", estadiaRepository.findAll());
        model.addAttribute("tipoHabitaciones", tipoHabitacionRepository.darTiposHabitaciones());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("habitaciones", habitacionRepository.findAll());  // Esta es la línea que agregamos
        return "reservaNuevo";
    }


    @PostMapping("/crearreserva/save")
    public String reservaGuardar(@ModelAttribute Reserva reserva) {
        reservaRepository.insertarReserva(reserva.getId(), reserva.getFechaLlegada(), reserva.getFechaSalida(), reserva.getCantidadPersonas(), reserva.getHotel().getId(), reserva.getPlanConsumo().getId(), reserva.getTipoHabitacion().getId(), reserva.getTitular().getCedula(), reserva.getHabitacion().getId());
        return "redirect:/reservas";
    }

    @GetMapping("/editarReserva/{id}")
    public String reservaEditarForm(@PathVariable("id") Long id, Model model) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva != null) {
            model.addAttribute("reserva", reserva);
            model.addAttribute("hoteles", hotelRepository.findAll());
            model.addAttribute("planesConsumo", planConsumoRepository.findAll());
            model.addAttribute("tipoHabitaciones", tipoHabitacionRepository.darTiposHabitaciones());
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("habitaciones", habitacionRepository.findAll());  // Esta es la línea que agregamos
            return "reservaEditar";
        } else {
            return "redirect:/reservas";
        }
    }

    
    @PostMapping("/editarReserva/{id}/save")
    public String reservaEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Reserva reserva) {
        reservaRepository.actualizarReserva(id, reserva.getFechaLlegada(), reserva.getFechaSalida(), reserva.getCantidadPersonas(), reserva.getHotel().getId(), reserva.getPlanConsumo().getId(), reserva.getTipoHabitacion().getId(), reserva.getTitular().getCedula(), reserva.getHabitacion().getId());
        return "redirect:/reservas";
    }

    @PostMapping("/eliminarReserva/{id}")
    public String reservaEliminar(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        try {
            reservaRepository.eliminarReserva(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttrs.addFlashAttribute("errorMessage", "No se puede eliminar la reserva porque está asociada a otra tabla.");
            return "redirect:/reservas";
        }
        return "redirect:/reservas";
    }

    @GetMapping("/porcentajeOcupacion")
    public String porcentajeOcupacion(Model model) {
        List<Object[]> resultados = reservaRepository.porcentajeOcupacionHabitaciones();
        model.addAttribute("ocupaciones", resultados);
        return "porcentajeOcupacion";
    }

}
