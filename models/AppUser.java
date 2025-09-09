package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role;

    @OneToOne
    private Client client;

    @OneToOne
    private Employee employee;

    @OneToOne
    private Admin admin;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

    public String getRole(){return role;}
    public void setRole(String role){this.role = role;}

    public Client getClient(){return client;}
    public void setClient(Client client){this.client = client;}

    public Employee getEmployee() {return employee;}
    public void setEmployee(Employee employee){this.employee = employee;}

    public Admin getAdmin() {return admin;}
    public void setAdmin(Admin admin) {this.admin = admin;} 

}
