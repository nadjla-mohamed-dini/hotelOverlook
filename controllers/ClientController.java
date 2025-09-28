package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.AppUser;
import com.example.demo.models.Client;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.AppUserRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.ReservationRepository;

@Controller
public class ClientController {

    private final AppUserRepository appUserRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;

    public ClientController(AppUserRepository appUserRepository,
                            ClientRepository clientRepository,
                            ReservationRepository reservationRepository) {
        this.appUserRepository = appUserRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
    }

    // Page d'accueil du client
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        String email = principal.getName();
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Client client = clientRepository.findByUser(user);
        model.addAttribute("client", client);

        return "home_page_client"; // juste le menu et info accueil
    }

    // Page des réservations du client
    @GetMapping("/client/reservations")
    public String reservations(Model model, Principal principal) {
        String email = principal.getName();
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Client client = clientRepository.findByUser(user);
        List<Reservation> reservations = reservationRepository.findByClient(client);

        model.addAttribute("client", client);
        model.addAttribute("reservations", reservations);

        return "list"; // tableau de ses réservations
    }
}
