package com.example.demo.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String name;
    private int capacity;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = true)
    private Reservation reservation;


    public Long getId() {return id;}
    public void setId(Long id){this.id = id;}

    public LocalDate getDate(){return date;}
    public void setDate(LocalDate date){this.date = date;}

    public int getCapacity(){return capacity;}
    public void setCapacity(int capacity){this.capacity = capacity;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}


    public Reservation getReservation() { return reservation;}
    public void setReservation(Reservation reservation) { this.reservation = reservation; }

}
