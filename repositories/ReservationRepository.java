package com.example.demo.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Client;
import com.example.demo.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByClientId(Long clientId);

    List<Reservation> findByClient(Client client);



    // Comptage des chambres occupées à une date donnée
    @Query(value = """
        SELECT COUNT(DISTINCT rm.id)
        FROM reservation r
        JOIN rooms rm ON r.room_id = rm.id
        WHERE :today BETWEEN r.date_start AND r.date_end
          AND r.status = 'CONFIRMED'
    """, nativeQuery = true)
    long countOccupiedRooms(@Param("today") LocalDateTime today);

    // Revenu total sur une période
    @Query(value = """
      SELECT SUM(
          (LEAST(r.date_end::date, :end) - GREATEST(r.date_start::date, :start) + 1) * rp.price
      )
      FROM reservation r
      JOIN rooms rm ON r.room_id = rm.id
      JOIN room_prices rp ON LOWER(rp.room_type) = LOWER(rm.type)
      WHERE r.date_start <= :end
        AND r.date_end >= :start
        AND r.status = 'CONFIRMED'
  """, nativeQuery = true)
  Double getTotalRevenue(@Param("start") java.sql.Date start,
                        @Param("end") java.sql.Date end);
  
  @Query(value = """
      WITH params AS (
      SELECT
            date_trunc('month', current_date)::date AS month_start, 
            (date_trunc('month', current_date) + INTERVAL '1 month - 1 day')::date AS month_end
            )
            SELECT
                (COUNT(DISTINCT r.room_id)::decimal / (SELECT COUNT(*) FROM rooms)) * 100 AS occupation_rate
            FROM reservation r, params p
            WHERE r.status = 'CONFIRMED'
              AND r.date_start::date <=p.month_end
              AND r.date_end >= p.month_start
      """, nativeQuery=true)
      Double getOccupationRateCurrentMonth();
  
  @Query(value = """
    SELECT COUNT(DISTINCT rm.id)
      FROM reservation r
      JOIN rooms rm ON r.room_id = rm.id
      WHERE r.date_start <= :end
        AND r.date_end >= :start
        AND r.status = 'CONFIRMED'
  """, nativeQuery = true)
  long countOccupiedRoomsBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

}