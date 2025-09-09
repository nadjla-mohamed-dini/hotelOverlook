package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "installations")
public class Installation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int capacity;
    private String type;

    public Installation() {
    }

    public Installation(int capacity, String type) {
        this.capacity = capacity;
        this.type = type;
    }

    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
}
