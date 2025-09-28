package com.example.demo.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.AppUser;
import com.example.demo.models.Client;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.AppUserRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.ReservationService;
import com.example.demo.services.RoomService;

@Controller
@RequestMapping("/reservations")
public class ReservationWebController {

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final AppUserRepository appUserRepository;
    private final ClientRepository clientRepository;


    public ReservationWebController(ReservationService reservationService,
                                    RoomService roomService,
                                    AppUserRepository appUserRepository,
                                    ClientRepository clientRepository) {
        this.reservationService = reservationService;

        this.roomService = roomService;
        this.appUserRepository = appUserRepository;
        this.clientRepository = clientRepository;


    }

    // ✅ Liste des réservations
    @GetMapping
    public String listReservations(Model model, Principal principal) {
    AppUser user = appUserRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    Client client = clientRepository.findByUser(user);
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
    public String newReservationForm(Model model, Principal principal) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("rooms", roomService.getAll());

        AppUser user = appUserRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Client client = clientRepository.findByUser(user);
    model.addAttribute("client", client);
        return "form"; // form.html (pas "reservations/form")
    }

    // ✅ Création d'une réservation
    @PostMapping
    public String createReservation(@RequestParam Long room,
                                    @ModelAttribute Reservation reservation,
                                    Principal principal) {
        AppUser user = appUserRepository.findByEmail(principal.getName())
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Client client = clientRepository.findByUser(user);

        reservation.setClient(client);
        reservation.setRoom(roomService.getById(room));
        reservationService.save(reservation);
        return "redirect:/client/reservations";
    }

    // ✅ Suppression
    @PostMapping("/{id}/delete")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
        return "redirect:/client/reservations";
    }
}
