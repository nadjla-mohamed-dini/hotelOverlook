package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Client;
import com.example.demo.models.Review;
import com.example.demo.models.Reservation;



public interface  ReviewRepository  extends JpaRepository<Review, Long>{
    List<Review> findByRating(int rating);
    List<Review> findByComment(String comment);
    List<Review> findByDate(LocalDate date);
    List<Review> findByReservation(Reservation reservation);
    List<Review> findByClient(Client client);
}
