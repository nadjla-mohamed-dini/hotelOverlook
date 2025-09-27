package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Client;
import com.example.demo.models.Employee;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.StatisticsService;

@Controller
public class AdminController {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final StatisticsService statisticsService;

    public AdminController (ClientRepository clientRepository, EmployeeRepository employeeRepository, StatisticsService statisticsService) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.statisticsService = statisticsService;
    }
    @GetMapping("/admin")
    public String dashbord(Model model) {
        List<Client> clients = clientRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();

        double occupation = statisticsService.getCurrentMonthOccupationRate();
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

        double revenue = statisticsService.getTotalRevenue(startOfMonth, endOfMonth);

        double satisfaction = statisticsService.getAverageSatisfaction();


        model.addAttribute("employees", employees);
        model.addAttribute("clients", clients);
        model.addAttribute("occupation", String.format("%.2f", occupation));
        model.addAttribute("revenue",String.format("%.2f", revenue));
        model.addAttribute("satisfaction",String.format("%.2f", satisfaction));

        return "welcome_admin";
    }
}
