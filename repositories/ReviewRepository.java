package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Client;
import com.example.demo.models.Reservation;
import com.example.demo.models.Review;



public interface  ReviewRepository  extends JpaRepository<Review, Long>{
    List<Review> findByRating(int rating);
    List<Review> findByComment(String comment);
    List<Review> findByDate(LocalDate date);
    List<Review> findByReservation(Reservation reservation);
    List<Review> findByClient(Client client);

    @Query(value = "SELECT AVG(rating) FROM reviews", nativeQuery = true)
    Double getAverageRating();

    @Query(value = """
            SELECT AVG(rating)
            FROM reviews
            WHERE date BETWEEN :start AND :end
        """, nativeQuery= true)
        Double getAverageRatingBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);



}
