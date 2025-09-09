package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;
    private int fidelity;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Room() {
    }

    public Long getId () {
        return id;
    }
    public void setId (Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFidelity() {
        return fidelity;
    }
    public void setFidelity(int fidelity) {
        this.fidelity = fidelity;
    }

    public Client getClient(){
        return client;
    }
    public void setClient(Client client){
        this.client = client;
    }
    
}
