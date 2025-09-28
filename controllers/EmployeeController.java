package com.example.demo.controllers;


import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.AppUser;
import com.example.demo.models.Employee;
import com.example.demo.models.Presence;
import com.example.demo.repositories.AppUserRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.PresenceRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;

@Controller
public class EmployeeController {

    private final PresenceRepository presenceRepository;
    private final ClientRepository clientRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final AppUserRepository appUserRepository;

    public EmployeeController(PresenceRepository presenceRepository,
                              ClientRepository clientRepository,
                              ReservationRepository reservationRepository,
                              RoomRepository roomRepository,
                              AppUserRepository appUserRepository) {
        this.presenceRepository = presenceRepository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.appUserRepository = appUserRepository;
    }

    // Page principale
    @GetMapping("/employee/dashboard")
    public String dashboard(Model model, Principal principal) {
        String email = principal.getName();
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Employee employee = user.getEmployee();
        List<Presence> presences = presenceRepository.findByEmployee(employee);

        model.addAttribute("presences", presences);
        model.addAttribute("newPresence", new Presence());

        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("reservations", reservationRepository.findAll());

        return "welcome_employee";
    }

    // Page secondaire : employee presence
    @GetMapping("/employee/presence")
    public String employeePresence(Model model, Principal principal) {
        String email = principal.getName();
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Employee employee = user.getEmployee();
        List<Presence> presences = presenceRepository.findByEmployee(employee);

        model.addAttribute("presences", presences);
        model.addAttribute("newPresence", new Presence());

        return "presence_employee"; // ton fichier employee_presence.html
    }

    @PostMapping("/employee/request")
    public String request(@ModelAttribute Presence newPresence, Principal principal) {
        String email = principal.getName();
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Employee employee = user.getEmployee();
        newPresence.setEmployee(employee);
        presenceRepository.save(newPresence);

        return "redirect:/employee/dashboard";
    }
}

