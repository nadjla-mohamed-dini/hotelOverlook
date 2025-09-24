package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.AppUser;
import com.example.demo.models.Client;
import com.example.demo.models.Reservation;
import com.example.demo.models.Review;
import com.example.demo.models.ReviewResponse;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.ReviewResponseRepository;
import com.example.demo.repositories.UserRepository;

@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final ReviewResponseRepository reviewResponseRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            ReservationRepository reservationRepository,
                            UserRepository userRepository,
                            ClientRepository clientRepository,
                            ReviewResponseRepository reviewResponseRepository) {
        this.reviewRepository = reviewRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.reviewResponseRepository = reviewResponseRepository;                        
    }

    
    @GetMapping("/reviews")
    public String listReview(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        AppUser user = userRepository.findByEmail(email).orElse(null);

        List<Reservation> userReservations = List.of();

        if (user != null) {
            Client client = clientRepository.findByUser(user).orElse(null);
            if (client != null) {
                userReservations = reservationRepository.findByClientId(client.getId());
            }
        }

        model.addAttribute("currentUser", user);  // <-- Ajouté ici
        model.addAttribute("reviews", reviewRepository.findAll());
        model.addAttribute("reservations", userReservations);
        model.addAttribute("reviewResponse", new ReviewResponse());
        model.addAttribute("review", new Review());

        return "reviews";
    }



    @PostMapping("/reviews/add")
    public String addReview(@ModelAttribute Review review,
                            @RequestParam Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            review.setReservation(reservation);
            reviewRepository.save(review);
        }

        return "redirect:/reviews";
    }

    @PostMapping("/reviews/response")
    public String responseToReview(@RequestParam Long reviewId,
                                   @RequestParam String response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        AppUser user = userRepository.findByEmail(email).orElse(null);
        if (user != null && user.getRole().equals("ADMIN")) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            if (review != null) {
                ReviewResponse reviewResponse = new ReviewResponse();
                reviewResponse.setReview(review);
                reviewResponse.setResponse(response);
                reviewResponseRepository.save(reviewResponse);
            }
        }
        return "redirect:/reviews"; 

    }
}
