package com.example.demo1.models;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String type; // simple, double, suite.

    @OneToMany(mappedBy="room", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    public Room() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * Calcule les points fidélité en fonction du type de chambre
     * Simple = 5, Double = 10, Suite = 20
     */
    public int getFidelityPoints() {
        return switch (type.toLowerCase()) {
            case "simple" -> 5;
            case "double" -> 10;
            case "suite" -> 20;
            default -> 0;
        };
    }

}
