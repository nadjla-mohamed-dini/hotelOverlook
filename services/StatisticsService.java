package com.example.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.RoomRepository;

@Service
public class StatisticsService {
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private ReservationRepository reservationRepository;

    public double getAverageSatisfaction() {
        Double avg = reviewRepository.getAverageRating();
        return avg !=null ? avg : 0.0;
    }

    public double getAverageStatisfactionBetween(LocalDate start, LocalDate end) {
        Double avg = reviewRepository.getAverageRatingBetween(start, end);
        return avg !=null ? avg :0.0;
    }

    public double getOccupationRate(LocalDateTime date) {
        long totalRooms = roomRepository.count();
        if (totalRooms == 0) return 0;

        long occupied = reservationRepository.countOccupiedRooms(date);
        return (occupied * 100.0) / totalRooms;
    }

    public double getTotalRevenue(LocalDate start, LocalDate end) {
        Double revenue = reservationRepository.getTotalRevenue(
            java.sql.Date.valueOf(start),
            java.sql.Date.valueOf(end)
        );
        return revenue != null ? revenue : 0.0;
        }

    public double getCurrentMonthOccupationRate() {
        long totalRooms = roomRepository.count();
        if (totalRooms == 0) return 0;

        LocalDate monthStart = LocalDate.now().withDayOfMonth(1);
        LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

        long occupied = reservationRepository.countOccupiedRoomsBetween(monthStart, monthEnd);
        return (occupied * 100.0) / totalRooms;
        }


    
}
