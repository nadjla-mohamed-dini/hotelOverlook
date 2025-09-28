package com.example.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.AppUser;
import com.example.demo.models.Client;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.UserRepository;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, 
                          ClientRepository clientRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "index"; 
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String lastName,
            Model model) {

        // Check whether the user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "This email address is already in use. !");
            return "index";
        }

        //  Create the User
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("CLIENT"); // default role
        userRepository.save(user);

        //  Create the client to bind this user
        Client client = new Client();
        client.setName(name);
        client.setLastName(lastName);
        client.setFidelity_point(0); // default
        client.setUser(user);
        clientRepository.save(client);

        model.addAttribute("success", "Registration successful, you can log in !");
        return "index";
    }
}
