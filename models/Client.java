package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private int fidelity_point;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    //getter et setter

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFidelity_point() {
        return fidelity_point;
    }
    public void setFidelity_point(int fidelity_point) {
        this.fidelity_point = fidelity_point;
    }
    
    public AppUser GetUser(){return user;}
    public void SetUser(AppUser user) {this.user = user;}

}
