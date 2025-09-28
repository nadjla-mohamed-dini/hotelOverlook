package com.example.demo.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "presence")
public class Presence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    private LocalDate dateStart;
    private LocalDate dateEnd;
    
    @Enumerated(EnumType.STRING)
    private AttendanceType type;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public LocalDate getDateStart() {return dateStart;}
    public void setDateStart(LocalDate dateStart){this.dateStart = dateStart;}

    public LocalDate getDateEnd(){return dateEnd;}
    public void setDateEnd(LocalDate dateEnd){this.dateEnd = dateEnd;}

    public AttendanceType getType() { return type; }
    public void setType(AttendanceType type) { this.type = type; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public enum AttendanceType {
    PRESENT,
    ABSENCE,
    DAY_OFF
    }    
}
