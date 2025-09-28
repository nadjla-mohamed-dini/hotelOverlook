package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usageinstallation")
public class UsageInstallation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_usage", nullable = false)
    private LocalDateTime dateUsage;

    @Column(name = "duration", nullable = false)
    private int duration; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "installation_id", nullable = false)
    private Installation installation;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDateUsage() { return dateUsage; }
    public void setDateUsage(LocalDateTime dateUsage) { this.dateUsage = dateUsage; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Installation getInstallation() { return installation; }
    public void setInstallation(Installation installation) { this.installation = installation; }
}
