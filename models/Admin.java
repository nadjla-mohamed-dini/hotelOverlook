package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean superAdmin;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean isSuperAdmin() { return superAdmin;}

    public void setSuperAdmin(boolean superAdmin) {this.superAdmin = superAdmin;}


    public AppUser GetUser(){return user;}
    public void SetUser(AppUser user) {this.user = user;}

}
