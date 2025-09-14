package com.example.demo.models;

import jakarta.persistence.Column;
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

    @Column(name = "fidelity_point", nullable = false)
    private int fidelity_point = 0; 

    @OneToOne
    @JoinColumn(name = "user_id") // clé étrangère vers users.id
    private AppUser user;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getFidelity_point() { return fidelity_point; }
    public void setFidelity_point(int fidelity_point) { this.fidelity_point = fidelity_point; }

    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }
}
