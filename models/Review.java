package com.example.demo.models;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int rating;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation; 
    //ajouter dans le mcd 
    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReviewResponse response;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date){this.date = date;}

    public int getRating(){return rating;}
    public void setRating(int rating){this.rating = rating;}

    public String getComment(){return comment;}
    public void setComment(String comment){this.comment = comment;}

    public Client getClient(){return client;}
    public void setClient(Client client){this.client = client;}

    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public ReviewResponse getResponse() {
    return response;
    }

    public void setResponse(ReviewResponse response) {
        this.response = response;
    }

}
