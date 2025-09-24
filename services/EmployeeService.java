package com.example.demo.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
}
