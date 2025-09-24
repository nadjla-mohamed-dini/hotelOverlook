package com.example.demo.models;

import java.time.LocalDate;
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
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String name;
    private int capacity;
    
    //changer la relation 
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();


    public Long getId() {return id;}
    public void setId(Long id){this.id = id;}

    public LocalDate getDate(){return date;}
    public void setDate(LocalDate date){this.date = date;}

    public int getCapacity(){return capacity;}
    public void setCapacity(int capacity){this.capacity = capacity;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}


    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }

}
