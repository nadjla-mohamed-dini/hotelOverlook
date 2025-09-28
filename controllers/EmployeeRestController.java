package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Employee;
import com.example.demo.services.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET /api/employee → Liste tous les employés
    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployee();
    }

    // GET /api/employee/{id} → Affiche un employé spécifique
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    // POST /api/employee → Crée un nouvel employé
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // PUT /api/employee/{id} → Met à jour un employé existant
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    // DELETE /api/employee/{id} → Supprime un employé
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
