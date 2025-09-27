package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Reservation;
import com.example.demo.services.ClientService;
import com.example.demo.services.ReservationService;
import com.example.demo.services.RoomService;

@Controller
@RequestMapping("/reservations")
public class ReservationWebController {

    private final ReservationService reservationService;
    private final ClientService clientService;
    private final RoomService roomService;

    public ReservationWebController(ReservationService reservationService,
                                    ClientService clientService,
                                    RoomService roomService) {
        this.reservationService = reservationService;
        this.clientService = clientService;
        this.roomService = roomService;
    }

    // ✅ Liste des réservations
    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAll()); // la liste
        return "list"; // list.html
    }

    // ✅ Détail d'une réservation
    @GetMapping("/{id}")
    public String viewReservation(@PathVariable Long id, Model model) {
        Reservation res = reservationService.getById(id);
        if (res == null) {
            return "redirect:/reservations";
        }
        model.addAttribute("reservation", res);
        return "view"; // view.html
    }

    // ✅ Formulaire de création
    @GetMapping("/new")
    public String newReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("rooms", roomService.getAll());
        return "form"; // form.html (pas "reservations/form")
    }

    // ✅ Création d'une réservation
    @PostMapping
    public String createReservation(@RequestParam Long client,
                                    @RequestParam Long room,
                                    @ModelAttribute Reservation reservation) {
        reservation.setClient(clientService.getClientById(client));
        reservation.setRoom(roomService.getById(room));
        reservationService.save(reservation);
        return "redirect:/reservations";
    }

    // ✅ Suppression
    @PostMapping("/{id}/delete")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
        return "redirect:/reservations";
    }
}
