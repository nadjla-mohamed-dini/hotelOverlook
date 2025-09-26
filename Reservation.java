package com.example.demo1.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;

    @Column(name = "date_end", nullable = false)
    private LocalDate dateEnd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // ajouter la relation sur post gres aussi dans le mcd et mpd 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id", nullable=false)
    private Room room
    ;
    @Column(name="paid_room_price")
    private double paidRoomPrice;
    @Column(name="paid_event_price")
    private double paidEventPrice;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = true)
    private Event event;

    // Enum pour le statut
    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDateStart() { return dateStart; }
    public void setDateStart(LocalDate dateStart) { this.dateStart = dateStart; }

    public LocalDate getDateEnd() { return dateEnd; }
    public void setDateEnd(LocalDate dateEnd) { this.dateEnd = dateEnd; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public double getPaidRoomPrice() { return paidRoomPrice; }
    public void setPaidRoomPrice(double paidRoomPrice) { this.paidRoomPrice = paidRoomPrice; }

    public double getPaidEventPrice() { return paidEventPrice; }
    public void setPaidEventPrice(double paidEventPrice) { this.paidEventPrice = paidEventPrice; }


    public String getReservationLabel() {
        if (room != null) {
            return "Chambre " + room.getType() + " (n° " + room.getNumber() + ")";
        }
        if (event != null) {
            return "Événement : " + event.getName();
        }
        return "Réservation inconnue";
    }
}
