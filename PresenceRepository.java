package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Employee;
import com.example.demo.models.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findByEmployeeAndDateStart (Employee employee, LocalDate dateStart);
    
    List<Presence> findByEmployee (Employee employee);
}
