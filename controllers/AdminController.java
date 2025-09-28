package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Client;
import com.example.demo.models.Employee;
import com.example.demo.models.Reservation;
import com.example.demo.models.Room;
import com.example.demo.models.RoomPrice;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.ReservationRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.roomPriceRepository;
import com.example.demo.services.StatisticsService;

@Controller
public class AdminController {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final StatisticsService statisticsService;
    private final roomPriceRepository roomPriceRepository;


    public AdminController(
            ClientRepository clientRepository,
            EmployeeRepository employeeRepository,
            RoomRepository roomRepository,
            ReservationRepository reservationRepository,
            StatisticsService statisticsService,
            roomPriceRepository roomPriceRepository) {

        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.statisticsService = statisticsService;
        this.roomPriceRepository = roomPriceRepository;

    }

    @GetMapping("/admin")
    public String dashboard(Model model) {
        
        List<Client> clients = clientRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();

        
        List<Room> rooms = roomRepository.findAll();

    
        List<Reservation> reservations = reservationRepository.findAll();


        
        double occupation = statisticsService.getCurrentMonthOccupationRate();
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        double revenue = statisticsService.getTotalRevenue(startOfMonth, endOfMonth);
        double satisfaction = statisticsService.getAverageSatisfaction();

        
        model.addAttribute("clients", clients);
        model.addAttribute("employees", employees);
        model.addAttribute("rooms", rooms);
        model.addAttribute("reservations", reservations);

        model.addAttribute("occupation", String.format("%.2f", occupation));
        model.addAttribute("revenue", String.format("%.2f", revenue));
        model.addAttribute("satisfaction", String.format("%.2f", satisfaction));

        return "welcome_admin"; 
    }
    @GetMapping("/admin/rooms/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomRepository.deleteById(id);
        return "redirect:/admin";
    }
    @GetMapping("/admin/rooms/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model) {
        Room room = roomRepository.findById(id).orElseThrow();
        model.addAttribute("room", room);
        return "edit_room"; // page Thymeleaf pour modification
    }

    @GetMapping("/admin/rooms")
    public String listRooms(Model model) {
        List<Room> rooms = roomRepository.findAll();
        Map<Long, Double> roomPricesMap = new HashMap<>();

        for (Room room : rooms) {
            RoomPrice rp = roomPriceRepository.findByRoomTypeIgnoreCase(room.getType());
            if (rp != null) {
                roomPricesMap.put(room.getId(), rp.getPrice());
            } else {
                roomPricesMap.put(room.getId(), 0.0);
            }
        }

        model.addAttribute("rooms", rooms);
        model.addAttribute("roomPricesMap", roomPricesMap);
        return "admin_rooms";
}


        @GetMapping("/admin/reservations")
    public String listReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        return "admin_reservations"; // page Thymeleaf
    }

    @GetMapping("/admin/reservations/edit/{id}")
    public String editReservation(@PathVariable Long id, Model model) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        model.addAttribute("reservation", reservation);
        return "edit_reservation"; // page Thymeleaf pour modification
    }

    @GetMapping("/admin/reservations/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
        return "redirect:/admin/reservations";
    }

    @PostMapping("/admin/reservations/update")
    public String updateReservation(@ModelAttribute Reservation reservation) {
        reservationRepository.save(reservation);
        return "redirect:/admin/reservations";
    }

   

}
