package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Employee;
import com.example.demo.models.Presence;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.PresenceRepository;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PresenceRepository presenceRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PresenceRepository presenceRepository) {
        this.employeeRepository = employeeRepository;
        this.presenceRepository = presenceRepository;
    }
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getUser() != null && employeeRepository.existsByUserId(employee.getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cet utilisateur est déjà associé à un employé !");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public List<Presence> getEmployePresenceByDate (Employee employee, LocalDate datestart) {
        return presenceRepository.findByEmployeeAndDateStart(employee, datestart);
    }

    public List<Presence> getEmployeePresence(Employee employee) {
        return presenceRepository.findByEmployee(employee);
    }

    public Presence requestAttendance(Employee employee, LocalDate start, LocalDate end, Presence.AttendanceType type) {
        Presence presence = new Presence();
        presence.setEmployee(employee);
        presence.setDateStart(start);
        presence.setDateEnd(end);
        presence.setType(type);
        return presenceRepository.save(presence);
    }

}
