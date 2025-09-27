package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.StatisticsService;

@RestController
public class StatisticController {
    
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("api/statistics/occupation")
    public double getOccupation(@RequestParam(required=false) String date) {
        LocalDateTime dt = (date != null) ? LocalDateTime.parse(date) : LocalDateTime.now();
        return statisticsService.getOccupationRate(dt);
    }

    @GetMapping("/api/statistics/satisfaction")
    public double getSatifaction() {
        return statisticsService.getAverageSatisfaction();
    }

    @GetMapping("api/statistics/revenue")
    public double getRevenue(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return statisticsService.getTotalRevenue(startDate, endDate);
    }

    @GetMapping("api/statistics/satisfaction-period")
    public double getSatiisfactionPeriod(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return statisticsService.getAverageStatisfactionBetween(startDate, endDate);
    }
}
