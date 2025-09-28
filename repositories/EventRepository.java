package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    
}
