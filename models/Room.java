package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;   
    private String type;     
    //private double price;
       
    // changer la relation sur postgres et dans le mcd et mpd 
    @OneToMany(mappedBy="room", fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private List<Reservation> reservation = new ArrayList<>();

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
        return reservation;
    }

    public void setReservations(List<Reservation> reservation) {
        this.reservation = reservation;
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
